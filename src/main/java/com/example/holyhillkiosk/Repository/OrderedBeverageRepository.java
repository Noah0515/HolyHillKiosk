package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.OrderedBeverage;
import com.example.holyhillkiosk.Entity.OrderedMenuId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedBeverageRepository extends CrudRepository<OrderedBeverage, OrderedMenuId> {
}
