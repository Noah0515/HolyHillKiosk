package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.DTO.RemainFoodOrderDTO;
import com.example.holyhillkiosk.Entity.FoodOrder;
import com.example.holyhillkiosk.Repository.FoodOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodOrderService {
    private final FoodOrderRepository foodOrderRepository;


    public FoodOrderService(FoodOrderRepository foodOrderRepository) {
        this.foodOrderRepository = foodOrderRepository;
    }

    public FoodOrder saveFoodOrder(FoodOrder foodOrder){
        return foodOrderRepository.save(foodOrder);
    }

    public List<RemainFoodOrderDTO> findRemainFoodOrder(){
        List<Object[]> results = foodOrderRepository.findRemainFoodOrder();
        return results.stream()
                .map(row -> new RemainFoodOrderDTO(
                        convertToLong(row[0]), // orderId
                        (String) row[1],              // foodName
                        ((Number) row[2]).intValue(), // foodNum
                        convertToString(row[3]),              // optionChoice
                        (String) row[4],
                        ((Number) row[6]).intValue()// foodOrderId
                ))
                .collect(Collectors.toList());
    }

    private String convertToString(Object value) {
        if (value instanceof Boolean) {
            return value.toString();  // Boolean을 String으로 변환
        }
        return value != null ? value.toString() : "";  // null 처리
    }

    private Long convertToLong(Object value) {
        if (value instanceof String) {
            try {
                // CHAR(3) 형식의 문자열을 숫자로 변환
                return Long.parseLong((String) value);  // 001 -> 1로 변환
            } catch (NumberFormatException e) {
                // 예외 처리: 잘못된 문자열이 들어왔을 경우
                return 0L;  // 적절한 기본값 설정
            }
        }
        return 0L; // 기본값 반환
    }

    private Integer convertToInt(Object value) {
        if (value instanceof String) {
            try {
                // CHAR(3) 형식의 문자열을 숫자로 변환
                return Integer.parseInt((String) value);  // 001 -> 1로 변환
            } catch (NumberFormatException e) {
                // 예외 처리
                return 0;  // 기본값
            }
        }
        return 0; // 기본값
    }

    public List<Object[]> findOrdersPK(String pk){
        return foodOrderRepository.findOrdersPK(pk);
    }

    public void updateComplete(String pk){
        foodOrderRepository.updateComplete(pk);
    }
}
