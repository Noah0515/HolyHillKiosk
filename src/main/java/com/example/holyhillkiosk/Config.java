package com.example.holyhillkiosk;

import com.example.holyhillkiosk.WebSocket.BeverageWebSocketHandler;
import com.example.holyhillkiosk.WebSocket.WebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public WebSocketHandler webSocketHandler(){
        return new WebSocketHandler();
    }
    @Bean
    public BeverageWebSocketHandler beverageWebSocketHandler(){
        return new BeverageWebSocketHandler();
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
