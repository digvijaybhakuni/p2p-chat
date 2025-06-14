package com.digvijayb.p2p.chat.config;

import com.digvijayb.p2p.chat.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class PresenceEventListener {
    @Autowired
    private PresenceService presenceService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String username = sha.getNativeHeader("user") != null && !sha.getNativeHeader("user").isEmpty()
                ? sha.getNativeHeader("user").get(0)
                : null;
        if (username != null) {
            presenceService.setUserOnline(username);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String username = sha.getSessionAttributes() != null ? (String) sha.getSessionAttributes().get("user") : null;
        if (username != null) {
            presenceService.setUserOffline(username);
        }
    }
}
