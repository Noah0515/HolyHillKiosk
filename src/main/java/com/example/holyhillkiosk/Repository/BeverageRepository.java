package com.example.holyhillkiosk.Repository;

import com.example.holyhillkiosk.Entity.Beverage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeverageRepository extends CrudRepository<Beverage, Integer> {
}
