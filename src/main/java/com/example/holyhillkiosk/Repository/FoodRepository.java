package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends CrudRepository<Food, Integer> {
    @Query(value = """
            SELECT foodname, SUM(foodnum)
            FROM food, orderedfood
            WHERE food.foodcode = orderedfood.foodcode
            GROUP BY food.foodcode
            """, nativeQuery = true)
    List<Object[]> findFoodNameAndSum();
}
