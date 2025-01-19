package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.FoodOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderRepository extends CrudRepository<FoodOrder, String> {
    @Query(value = """
        SELECT CAST(fo.orderid AS CHAR) AS orderid, fi.foodname, fi.foodnum, fi.optionchoice, fo.foodorderid, fi.foodcode, fo.foodordercomplete
        FROM 
            (SELECT foodname, foodnum, optionchoice, foodorderid, food.foodcode
             FROM orderedfood, food
             WHERE orderedfood.foodcode = food.foodcode) AS fi,
            (SELECT foodorderid, orderid, foodordercomplete 
             FROM foodorder
             WHERE foodordercomplete < 2) AS fo
        WHERE 
            fi.foodorderid = fo.foodorderid
        order by fo.orderid, fi.foodcode;
        """, nativeQuery = true)
    List<Object[]> findRemainFoodOrder();

    @Query(value = """
    SELECT ordertime, orderid 
    FROM foodorder 
    WHERE foodorderid = :foodorderid
    """, nativeQuery = true)
    List<Object[]> findOrdersPK(@Param("foodorderid")String foodorderid);

    @Transactional
    @Modifying
    @Query(value = """
    update foodorder 
    set foodordercomplete = foodordercomplete + 1 
    where foodorderid = :foodorderid
    """, nativeQuery = true)
    void updateComplete(@Param("foodorderid") String foodorderid);

}
