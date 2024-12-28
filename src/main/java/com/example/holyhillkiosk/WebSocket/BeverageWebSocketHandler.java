package com.example.holyhillkiosk.WebSocket;

import com.example.holyhillkiosk.DTO.RemainBeverageOrderDTO;
import com.example.holyhillkiosk.DTO.RemainFoodOrderDTO;
import com.example.holyhillkiosk.Service.BeverageOrderService;
import com.example.holyhillkiosk.Service.FoodOrderService;
import com.example.holyhillkiosk.Service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class BeverageWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private BeverageWebSocketSessionManager beverageWebSocketSessionManager;
    @Autowired
    private BeverageOrderService beverageOrderService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(BeverageWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("웹소켓 연결됨: {}", session.getId());
        beverageWebSocketSessionManager.addSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        logger.info("수신 메시지: {}", message.getPayload());

        if (payload.equals("/refresh")) {  // "/refresh" 메시지 처리
            sendRemainBeverageOrderToClient(session);  // 남은 음식 주문 정보를 클라이언트에 전송
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            MyMessage myMessage = objectMapper.readValue(payload, MyMessage.class);

            System.out.println("Received message: " + myMessage.getMessage());
            System.out.println("Received data: " + myMessage.getData());

            if(myMessage.getMessage().equals("/process")){
                processBeverageOrder(myMessage.getData());
                sendRemainBeverageOrderToClient(session);
            }
        }

        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("웹소켓 연결 종료: {}", session.getId());
        beverageWebSocketSessionManager.removeSession(session);
    }

    private void sendRemainBeverageOrderToClient(WebSocketSession session) throws IOException {
        List<RemainBeverageOrderDTO> remainBeverageOrders = beverageOrderService.findRemainBeverageOrder();

        // JSON으로 변환
        String jsonResponse = objectMapper.writeValueAsString(remainBeverageOrders);
        System.out.println(jsonResponse);
        // 클라이언트로 JSON 데이터를 보냄
        session.sendMessage(new TextMessage(jsonResponse));
    }

    private void processBeverageOrder(String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // jsonData는 JSON 배열 형식이라 가정
        List<String> orderIds = objectMapper.readValue(jsonData, new TypeReference<List<String>>(){});

        ;
        for(String orderId: orderIds){
            List<Object[]> result = beverageOrderService.findOrdersPK(orderId);
            Object[] row = result.get(0);

            Timestamp ordertime = (Timestamp ) row[0];
            String orderid = (String) row[1];
            String formattedOrderid = String.format("%03d", Integer.parseInt(orderid));

            orderService.completeOrder(ordertime, formattedOrderid);
            beverageOrderService.updateComplete(orderId);

            System.out.println("Order Time: " + ordertime);
            System.out.println("Order ID: " + formattedOrderid);
        }

    }
}
