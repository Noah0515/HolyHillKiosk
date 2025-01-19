package com.example.holyhillkiosk.DTO;

import lombok.Getter;

@Getter
public class RemainFoodOrderDTO {
    private Long orderId;
    private String foodName;
    private int foodNum;
    private String optionChoice;
    private String foodOrderId;
    private int foodOrderComplete;

    public RemainFoodOrderDTO(Long orderId, String foodName, int foodNum, String optionChoice, String foodOrderId, int foodOrderComplete) {
        this.orderId = orderId;
        this.foodName = foodName;
        this.foodNum = foodNum;
        this.optionChoice = optionChoice;
        this.foodOrderId = foodOrderId;
        this.foodOrderComplete = foodOrderComplete;
    }

    public String toString(){
        return "orderiId = " + orderId + ", foodName = " + foodName + ", foodNum = " + foodNum + ", optionChoice = " + optionChoice + ", foodOrderId = " + foodOrderId + ", foodOrderComplete = " + foodOrderComplete;
    }
}