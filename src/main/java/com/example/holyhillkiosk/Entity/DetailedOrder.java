package com.example.holyhillkiosk.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class DetailedOrder {
    //db 테이블에 매핑될 멤버변수

    protected LocalDateTime orderTime; //주문시각. Orders의 orderTime과 fk

    protected String orderId; //주문번호. Orders의 OrderId와 fk
    @Id
    protected String detailOrderId; //상세주문번호. 메뉴타입에 따라 다른 Id를 자식에서 부여
    //protected boolean orderComplete; //제공완료를 나타냄
    protected int orderComplete;

    //생성자
    public DetailedOrder(){

    }

    public DetailedOrder(OrdersId id){
        orderTime = id.getOrderTime();
        orderId = id.getOrderId();
        //orderComplete = false;
        orderComplete = 0;
    }

    public String getDetailOrderId() {
        return detailOrderId;
    }

    //메소드

}
