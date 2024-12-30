package com.example.holyhillkiosk.Controller;

import com.example.holyhillkiosk.DTO.OrderedBeverageDTO;
import com.example.holyhillkiosk.DTO.OrderedFoodDTO;
import com.example.holyhillkiosk.DTO.RemainBeverageOrderDTO;
import com.example.holyhillkiosk.DTO.RemainFoodOrderDTO;
import com.example.holyhillkiosk.Entity.*;
import com.example.holyhillkiosk.Service.*;
import com.example.holyhillkiosk.WebSocket.BeverageWebSocketSessionManager;
import com.example.holyhillkiosk.WebSocket.WebSocketSessionManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final FoodOrderService foodOrderService;
    private final BeverageOrderService beverageOrderService;
    private final OrderedFoodService orderedFoodService;
    private final OrderedBeverageService orderedBeverageService;
    private final NotificationService notificationService;

    @Autowired
    private WebSocketSessionManager sessionManager;
    @Autowired
    private BeverageWebSocketSessionManager beverageWebSocketSessionManager;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    public OrderController(OrderService orderService, FoodOrderService foodOrderService, BeverageOrderService beverageOrderService, OrderedFoodService orderedFoodService, OrderedBeverageService orderedBeverageService, NotificationService notificationService) {
        this.orderService = orderService;
        this.foodOrderService = foodOrderService;
        this.beverageOrderService = beverageOrderService;
        this.orderedFoodService = orderedFoodService;
        this.orderedBeverageService = orderedBeverageService;
        this.notificationService = notificationService;
    }


    @PostMapping("/customer/submit-order")
    public Map<String, Object> postCustomerSubmitOrder(@RequestBody OrderData orderData, RedirectAttributes redirectAttributes) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<OrderedFoodDTO> foodOrderDatas = objectMapper.convertValue(orderData.getFoodOrder(), new TypeReference<List<OrderedFoodDTO>>() {});
        List<OrderedBeverageDTO> beverageOrderDatas = objectMapper.convertValue(orderData.getBeverageOrder(), new TypeReference<List<OrderedBeverageDTO>>() {});

        String foodOrderId = null;
        String beverageOrderId = null;

        if(!foodOrderDatas.isEmpty()){
            Orders order = new Orders();
            System.out.println("음식주문 있음");
            System.out.println(order.toString());
            FoodOrder foodOrder = new FoodOrder(order.getOrdersId());
            foodOrderId = order.getOrderId();
            List<OrderedFood> orderedFoods = new ArrayList<>();

            for(OrderedFoodDTO foodOrderData: foodOrderDatas){
                OrderedFood temp = new OrderedFood(foodOrder.getDetailOrderId(), foodOrderData.getMenuCode(), foodOrderData.getQty(), foodOrderData.isOption());
                orderedFoods.add(temp);
                //System.out.println(temp.toString());
            }
            orderService.savaOrder(order);
            foodOrderService.saveFoodOrder(foodOrder);
            orderedFoodService.saveOrderedFoodAll(orderedFoods);
            System.out.println(orderedFoods.toString());


            // 추가적으로 기존 메서드를 사용해서 남은 음식 주문 상태를 클라이언트에게 전송
            for (WebSocketSession session : sessionManager.getSessions()) {
                // 각 클라이언트 세션에 대해 데이터 전송
                sendRemainFoodOrderToClient(session);
            }


        }

        if(!beverageOrderDatas.isEmpty()){
            Orders order = new Orders();
            System.out.println("음료주문 있음");
            System.out.println(order.toString());
            BeverageOrder beverageOrder = new BeverageOrder(order.getOrdersId());
            beverageOrderId = order.getOrderId();
            List<OrderedBeverage> orderedBeverages = new ArrayList<>();

            for(OrderedBeverageDTO beverageOrderData: beverageOrderDatas){
                OrderedBeverage temp = new OrderedBeverage(beverageOrder.getDetailOrderId(), beverageOrderData.getMenuCode(), beverageOrderData.getQty());
                orderedBeverages.add(temp);
                System.out.println(temp.toString());
            }

            orderService.savaOrder(order);
            beverageOrderService.saveBeverageOrder(beverageOrder);
            orderedBeverageService.saveOrderedBeverageAll(orderedBeverages);
            System.out.println(orderedBeverages.toString());

        }

        Map<String, Object> response = new HashMap<>();
        response.put("foodOrderId", foodOrderId);
        response.put("beverageOrderId", beverageOrderId);
        response.put("redirectUrl", "/customer/order-complete?status=success");
        System.out.println(response);
        return ResponseEntity.ok(response).getBody();

    };
    @PostMapping("/staff/submit-order")
    public Map<String, Object> postStaffSubmitOrder(@RequestBody OrderData orderData, RedirectAttributes redirectAttributes) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<OrderedFoodDTO> foodOrderDatas = objectMapper.convertValue(orderData.getFoodOrder(), new TypeReference<List<OrderedFoodDTO>>() {});
        List<OrderedBeverageDTO> beverageOrderDatas = objectMapper.convertValue(orderData.getBeverageOrder(), new TypeReference<List<OrderedBeverageDTO>>() {});

        String foodOrderId = null;
        String beverageOrderId = null;

        if(!foodOrderDatas.isEmpty()){
            Orders order = new Orders();
            System.out.println("음식주문 있음");
            System.out.println(order.toString());
            FoodOrder foodOrder = new FoodOrder(order.getOrdersId());
            foodOrderId = order.getOrderId();
            List<OrderedFood> orderedFoods = new ArrayList<>();

            for(OrderedFoodDTO foodOrderData: foodOrderDatas){
                OrderedFood temp = new OrderedFood(foodOrder.getDetailOrderId(), foodOrderData.getMenuCode(), foodOrderData.getQty(), foodOrderData.isOption());
                orderedFoods.add(temp);
                //System.out.println(temp.toString());
            }
            orderService.savaOrder(order);
            foodOrderService.saveFoodOrder(foodOrder);
            orderedFoodService.saveOrderedFoodAll(orderedFoods);
            System.out.println(orderedFoods.toString());


            // 추가적으로 기존 메서드를 사용해서 남은 음식 주문 상태를 클라이언트에게 전송
            for (WebSocketSession session : sessionManager.getSessions()) {
                // 각 클라이언트 세션에 대해 데이터 전송
                sendRemainFoodOrderToClient(session);
            }


        }

        if(!beverageOrderDatas.isEmpty()){
            Orders order = new Orders();
            System.out.println("음료주문 있음");
            System.out.println(order.toString());
            BeverageOrder beverageOrder = new BeverageOrder(order.getOrdersId());
            beverageOrderId = order.getOrderId();
            List<OrderedBeverage> orderedBeverages = new ArrayList<>();

            for(OrderedBeverageDTO beverageOrderData: beverageOrderDatas){
                OrderedBeverage temp = new OrderedBeverage(beverageOrder.getDetailOrderId(), beverageOrderData.getMenuCode(), beverageOrderData.getQty());
                orderedBeverages.add(temp);
                System.out.println(temp.toString());
            }

            orderService.savaOrder(order);
            beverageOrderService.saveBeverageOrder(beverageOrder);
            orderedBeverageService.saveOrderedBeverageAll(orderedBeverages);
            System.out.println(orderedBeverages.toString());

            // 추가적으로 기존 메서드를 사용해서 남은 음식 주문 상태를 클라이언트에게 전송
            for (WebSocketSession session : beverageWebSocketSessionManager.getSessions()) {
                // 각 클라이언트 세션에 대해 데이터 전송
                sendRemainBeverageOrderToClient(session);
            }

        }

        Map<String, Object> response = new HashMap<>();
        response.put("foodOrderId", foodOrderId);
        response.put("beverageOrderId", beverageOrderId);
        response.put("redirectUrl", "/staff/order-complete?status=success");
        System.out.println(response);
        return ResponseEntity.ok(response).getBody();

    };


    public static class OrderData {
        private Object foodOrder;
        private Object beverageOrder;

        // getter, setter 필요
        public Object getFoodOrder() {
            return foodOrder;
        }

        public void setFoodOrder(Object foodOrder) {
            this.foodOrder = foodOrder;
        }

        public Object getBeverageOrder() {
            return beverageOrder;
        }

        public void setBeverageOrder(Object beverageOrder) {
            this.beverageOrder = beverageOrder;
        }

        @Override
        public String toString() {
            return "OrderData{" +
                    "foodOrder=" + foodOrder +
                    ", beverageOrder=" + beverageOrder +
                    '}';
        }
    }
    // 기존 sendRemainFoodOrderToClient 메서드를 활용
    private void sendRemainFoodOrderToClient(WebSocketSession session) throws IOException {
        List<RemainFoodOrderDTO> remainFoodOrders = foodOrderService.findRemainFoodOrder();

        // JSON으로 변환

        String jsonResponse = objectMapper.writeValueAsString(remainFoodOrders);
        System.out.println(jsonResponse);
        // 클라이언트로 JSON 데이터를 보냄
        session.sendMessage(new TextMessage(jsonResponse));
    }

    private void sendRemainBeverageOrderToClient(WebSocketSession session) throws IOException {
        List<RemainBeverageOrderDTO> remainBeverageOrders = beverageOrderService.findRemainBeverageOrder();

        // JSON으로 변환
        String jsonResponse = objectMapper.writeValueAsString(remainBeverageOrders);
        System.out.println(jsonResponse);
        // 클라이언트로 JSON 데이터를 보냄
        session.sendMessage(new TextMessage(jsonResponse));
    }
}
