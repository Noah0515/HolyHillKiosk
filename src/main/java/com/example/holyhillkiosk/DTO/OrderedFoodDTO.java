package com.example.holyhillkiosk.DTO;

import lombok.Getter;

@Getter
public class OrderedFoodDTO {
    private int menuCode;
    private int qty;
    private boolean option;
    public OrderedFoodDTO(){

    }
    public OrderedFoodDTO(int menuCode, int qty, boolean option){
        this.menuCode = menuCode;
        this.qty = qty;
        this.option = option;
    }

}
