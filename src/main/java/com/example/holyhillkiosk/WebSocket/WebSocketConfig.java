package com.example.holyhillkiosk.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    private final BeverageWebSocketHandler beverageWebSocketHandler;
    public WebSocketConfig(WebSocketHandler webSocketHandler, BeverageWebSocketHandler beverageWebSocketHandler){
        this.webSocketHandler = webSocketHandler;
        this.beverageWebSocketHandler = beverageWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/wsf/staff/processFood")
                .setAllowedOrigins("*"); // CORS 허용 설정
        registry.addHandler(beverageWebSocketHandler, "/wsb/staff/processBeverage")
                .setAllowedOrigins("*"); // CORS 허용 설정
    }


}
