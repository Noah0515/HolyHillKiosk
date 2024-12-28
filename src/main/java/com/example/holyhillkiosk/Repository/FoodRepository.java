package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<Food, Integer> {

}
