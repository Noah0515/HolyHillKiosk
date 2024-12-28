package com.example.holyhillkiosk.Service;

import com.example.holyhillkiosk.WebSocket.WebSocketSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private WebSocketSessionManager sessionManager;

    public void notifyClients(String message) {
        System.out.println("클라이언트에게 메시지 전송: " + message);
        sessionManager.broadcastMessage("{\"type\":\"notification\", \"content\":\"" + message + "\"}");
    }
}
