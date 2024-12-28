package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.Entity.OrderedFood;
import com.example.holyhillkiosk.Repository.OrderedFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedFoodService {
    private final OrderedFoodRepository orderedFoodRepository;


    public OrderedFoodService(OrderedFoodRepository orderedFoodRepository) {
        this.orderedFoodRepository = orderedFoodRepository;
    }

    public List<OrderedFood> saveOrderedFoodAll(List<OrderedFood> orderedFoods){
        return (List<OrderedFood>) orderedFoodRepository.saveAll(orderedFoods);
    }
}
