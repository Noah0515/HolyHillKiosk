package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.OrderedFood;
import com.example.holyhillkiosk.Entity.OrderedMenuId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedFoodRepository extends CrudRepository<OrderedFood, OrderedMenuId> {

}
