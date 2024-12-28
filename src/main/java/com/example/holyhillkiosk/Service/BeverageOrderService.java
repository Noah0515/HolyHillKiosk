package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.DTO.RemainBeverageOrderDTO;
import com.example.holyhillkiosk.DTO.RemainFoodOrderDTO;
import com.example.holyhillkiosk.Entity.BeverageOrder;
import com.example.holyhillkiosk.Repository.BeverageOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeverageOrderService {
    private final BeverageOrderRepository beverageOrderRepository;

    public BeverageOrderService(BeverageOrderRepository beverageOrderRepository) {
        this.beverageOrderRepository = beverageOrderRepository;
    }

    public BeverageOrder saveBeverageOrder(BeverageOrder beverageOrder){
        return beverageOrderRepository.save(beverageOrder);
    }

    public List<RemainBeverageOrderDTO> findRemainBeverageOrder(){
        List<Object[]> results = beverageOrderRepository.findRemainBeverageOrder();
        return results.stream()
                .map(row -> new RemainBeverageOrderDTO(
                        convertToLong(row[0]), // orderId
                        (String) row[1],              // foodName
                        ((Number) row[2]).intValue(), // foodNum
                        (String) row[3] // foodOrderId
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
        return beverageOrderRepository.findOrdersPK(pk);
    }

    public void updateComplete(String pk){
        beverageOrderRepository.updateComplete(pk);
    }
}
