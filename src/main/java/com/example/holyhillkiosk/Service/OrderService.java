package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.Entity.Orders;
import com.example.holyhillkiosk.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Orders savaOrder(Orders order){
        return orderRepository.save(order);
    }

    public void completeOrder(Timestamp ordertime, String orderid) {
        orderRepository.updateOrderCompletion(ordertime, orderid);
    }
}
