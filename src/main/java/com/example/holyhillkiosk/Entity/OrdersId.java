package com.example.holyhillkiosk.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class OrdersId implements Serializable {
    //주문번호 생성을 위한 static멤버변수
    @Transient
    private static int curOrderNum = 0;
    @Transient
    private static int MAX_ORDER_NUM = 3000;
    @Transient
    private static LocalDate curDate = LocalDate.now();

    //db와 매핑되는 멤버변수
    @Column(name = "orderid")
    private String orderId; // 주문번호

    @Column(name = "ordertime")
    private LocalDateTime orderTime; //주문시각

    //생성자
    public OrdersId() {
        System.out.println("************************주문번호 생성*****************");
        orderTime = LocalDateTime.now();
        orderId = generateOrderId();
        System.out.println("************************생성된 주문번호 =" + orderId + "*****************");
    }

    // equals()와 hashCode() 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersId orderId = (OrdersId) o;
        return Objects.equals(orderTime, orderId.orderTime) && Objects.equals(orderId, orderId.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderTime, orderId);
    }

    //주문시각 반환
    public LocalDateTime getOrderTime(){
        return orderTime;
    }

    //주문번호 반환
    public String getOrderId(){
        return orderId;
    }



    //주문번호 생성
    public static synchronized String generateOrderId() {
        System.out.println("***********************주문번호 만들기*******************");
        LocalDate today = LocalDate.now();

        // 날짜가 변경되었으면 초기화
        if (!today.equals(curDate)) {
            curDate = today;
            curOrderNum = 0;
        }

        // 주문 번호 증가
        if (curOrderNum <= MAX_ORDER_NUM) {
            curOrderNum++;
        } else {
            curOrderNum = 1;
        }

        // 주문 번호를 문자열 형식으로 반환 (3자리, 앞에 0 채움)
        return String.format("%03d", curOrderNum/3 + 1);
    }
/*
    @PrePersist
    public void onPrePersist() {
        if (orderTime == null) {
            orderTime = LocalDateTime.now();
        }
        if (orderId == null) {
            orderId = generateOrderId();
        }
    }
*/
    public String toString(){
        return "orderTime = " + orderTime + ", orderId = " + orderId;
    }

}
