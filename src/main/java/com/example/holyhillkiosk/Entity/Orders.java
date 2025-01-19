package com.example.holyhillkiosk.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    //db와 매핑되는 멤버변수
    @EmbeddedId
    private OrdersId id; //Orders의 PK(orderTime, orderId)
    @Column(name="ordercomplete")
    //private boolean orderComplete; //완료된 주문인지
    private int orderComplete;
    //매핑 안되는 변수
    @OneToMany(mappedBy = "order")
    private List<FoodOrder> foodOrder = new ArrayList<>();
    @OneToMany(mappedBy = "order")
    private List<BeverageOrder> beverageOrders = new ArrayList<>();

    //생성자
    public Orders(){
        id = new OrdersId();
        //orderComplete = false;
        orderComplete = 0;
    }

    //주문시각 반환
    public LocalDateTime getOrderTime(){
        return id.getOrderTime();
    }

    //주문번호 반환
    public OrdersId getOrdersId(){
        return id;
    }
    public String getOrderId(){
        return id.getOrderId();
    }

    @PrePersist
    public void onPrePersist() {
        if (id == null) {
            id = new OrdersId();
        }
    }

    public String toString(){
        return id.toString() + ", orderComplete = " + orderComplete;
    }

}
