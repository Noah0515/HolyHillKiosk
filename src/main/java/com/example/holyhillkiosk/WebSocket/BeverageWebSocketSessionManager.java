package com.example.holyhillkiosk.WebSocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class BeverageWebSocketSessionManager {
    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    public void addSession(WebSocketSession session) {
        if(session != null){
            sessions.add(session);
        } else {
            throw new IllegalArgumentException("Session cannot be null");
        }
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public Set<WebSocketSession> getSessions() {
        return sessions;
    }

    public void broadcastMessage(String message) {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessageToAll(String message) throws IOException {
        synchronized (sessions) {  // synchronized 블록을 사용하여 Set 접근을 동기화
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            }
        }
    }
}
