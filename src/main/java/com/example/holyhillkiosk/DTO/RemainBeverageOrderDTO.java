package com.example.holyhillkiosk.DTO;

import lombok.Getter;

@Getter
public class RemainBeverageOrderDTO {
    private Long orderId;
    private String beverageName;
    private int beverageNum;
    private String beverageOrderId;
    private int beverageOrderComplete;

    public RemainBeverageOrderDTO(Long orderId, String beverageName, int beverageNum, String beverageOrderId, int beverageOrderComplete) {
        this.orderId = orderId;
        this.beverageName = beverageName;
        this.beverageNum = beverageNum;
        this.beverageOrderId = beverageOrderId;
        this.beverageOrderComplete = beverageOrderComplete;
    }

    public String toString(){
        return "orderiId = " + orderId + ", beverageName = " + beverageName + ", beverageNum = " + beverageNum + ", beverageOrderId = " + beverageOrderId + ", beverageOrderComplete = " + beverageOrderComplete;
    }
}