package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.Entity.OrderedBeverage;
import com.example.holyhillkiosk.Repository.OrderedBeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedBeverageService {
    private final OrderedBeverageRepository orderedBeverageRepository;


    public OrderedBeverageService(OrderedBeverageRepository orderedBeverageRepository) {
        this.orderedBeverageRepository = orderedBeverageRepository;
    }
    public List<OrderedBeverage> saveOrderedBeverageAll(List<OrderedBeverage> orderedBeverages){
        return (List<OrderedBeverage>)orderedBeverageRepository.saveAll(orderedBeverages);
    }
}
