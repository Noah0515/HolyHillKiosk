package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.DTO.BeverageStatisticDTO;
import com.example.holyhillkiosk.DTO.FoodStatisticDTO;
import com.example.holyhillkiosk.Repository.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeverageService {
    private final BeverageRepository beverageRepository;


    public BeverageService(BeverageRepository beverageRepository) {
        this.beverageRepository = beverageRepository;
    }

    public List<BeverageStatisticDTO> getBeverageStatistic() {
        List<Object[]> results = beverageRepository.findBeverageNameAndSum();
        return results.stream()
                .map(row -> new BeverageStatisticDTO(
                        (String) row[0],
                        ((BigDecimal) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
}
