package com.example.holyhillkiosk.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class OrderedMenu {
    //db에 매핑될 변수들
    protected int menuNum;

    //생성자
    public OrderedMenu(){

    }

    public OrderedMenu(int num){
        menuNum = num;
    }
}
