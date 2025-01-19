package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.DTO.FoodStatisticDTO;
import com.example.holyhillkiosk.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {
    private final FoodRepository foodRepository;


    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<FoodStatisticDTO> getFoodStatistic() {
        List<Object[]> results = foodRepository.findFoodNameAndSum();
        return results.stream()
                .map(row -> new FoodStatisticDTO(
                        (String) row[0],
                        ((BigDecimal) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
}
