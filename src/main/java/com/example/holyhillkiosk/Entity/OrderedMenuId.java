package com.example.holyhillkiosk.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderedMenuId implements Serializable {
    //db에 매핑될 번수
    @Column(name="menuorderid")
    private String menuOrderId; //상세주문번호
    @Column(name="menucode")
    private int menuCode; //메뉴코드

    //생성자
    public OrderedMenuId(){

    }

    public OrderedMenuId(String id, int code){
        menuOrderId = id;
        menuCode = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedMenuId orderKey = (OrderedMenuId) o;
        return Objects.equals(menuOrderId, orderKey.menuOrderId) &&
                Objects.equals(menuCode, orderKey.menuCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuOrderId, menuCode);
    }

    @Override
    public String toString(){
        return "orderId = " + menuOrderId + ", menuCode = " + menuCode;
    }
}
