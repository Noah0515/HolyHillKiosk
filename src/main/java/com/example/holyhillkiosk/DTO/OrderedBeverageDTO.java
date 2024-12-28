package com.example.holyhillkiosk.DTO;

import lombok.Getter;

@Getter
public class OrderedBeverageDTO {
    private int menuCode;
    private int qty;
    public OrderedBeverageDTO(){

    }
    public OrderedBeverageDTO(int menuCode, int qty){
        this.menuCode = menuCode;
        this. qty = qty;
    }
}
