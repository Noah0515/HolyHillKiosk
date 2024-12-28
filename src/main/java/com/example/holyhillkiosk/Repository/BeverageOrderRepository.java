package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.BeverageOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeverageOrderRepository extends CrudRepository<BeverageOrder, String> {
    @Query(value = """
            SELECT CAST(bo.orderid AS CHAR) AS orderid, bi.beveragename, bi.beveragenum, bo.beverageorderid, bi.beveragecode
            FROM (
                SELECT beveragename, beveragenum, beverageorderid, beverage.beveragecode
                FROM orderedbeverage
                JOIN beverage ON orderedbeverage.beveragecode = beverage.beveragecode
            ) AS bi,
            (
                SELECT beverageorderid, orderid
                FROM beverageorder
                WHERE beverageordercomplete = false
            ) AS bo
            WHERE bi.beverageorderid = bo.beverageorderid
            ORDER BY bo.orderid, bi.beveragecode
            """, nativeQuery = true)
    List<Object[]> findRemainBeverageOrder();

    @Query(value = """
    SELECT ordertime, orderid 
    FROM beverageorder 
    WHERE beverageorderid = :beverageorderid
    """, nativeQuery = true)
    List<Object[]> findOrdersPK(@Param("beverageorderid")String beverageorderid);

    @Transactional
    @Modifying
    @Query(value = """
    update beverageorder 
    set beverageordercomplete = true 
    where beverageorderid = :beverageorderid
    """, nativeQuery = true)
    void updateComplete(@Param("beverageorderid") String beverageorderid);
}
