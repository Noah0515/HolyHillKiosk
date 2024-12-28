package com.example.holyhillkiosk.DTO;

import lombok.Getter;

@Getter
public class RemainBeverageOrderDTO {
    private Long orderId;
    private String beverageName;
    private int beverageNum;
    private String beverageOrderId;

    public RemainBeverageOrderDTO(Long orderId, String beverageName, int beverageNum, String beverageOrderId) {
        this.orderId = orderId;
        this.beverageName = beverageName;
        this.beverageNum = beverageNum;
        this.beverageOrderId = beverageOrderId;
    }

    public String toString(){
        return "orderiId = " + orderId + ", beverageName = " + beverageName + ", beverageNum = " + beverageNum + ", beverageOrderId = " + beverageOrderId;
    }
}