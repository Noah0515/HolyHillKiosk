package com.example.holyhillkiosk.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Menu {
    //db의 테이블과 매핑될 멤버변수
    @Id
    protected int menuCode; //메뉴코드
    protected String menuName; //메뉴이름

    //생성자
    public Menu(){

    }

    public Menu(int code, String name){
        menuCode = code;
        menuName = name;
    }


}
