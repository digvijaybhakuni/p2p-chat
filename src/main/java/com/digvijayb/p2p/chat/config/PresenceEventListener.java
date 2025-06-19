package com.digvijayb.p2p.chat.config;

import com.digvijayb.p2p.chat.service.PresenceService;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
public class PresenceEventListener {
    @Autowired
    private PresenceService presenceService;

    private ConcurrentHashMap<String, String> sessions = new ConcurrentHashMap<>();

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        log.info("handleConnectWS : {}", event);
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String username = sha.getNativeHeader("user") != null && !sha.getNativeHeader("user").isEmpty()
                ? sha.getNativeHeader("user").get(0)
                : null;
        if (username != null) {
            sessions.put(sha.getSessionId(), username);
            log.info("{} : {}", sha.getSessionId(), username);
            presenceService.setUserOnline(username);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        log.info("handleDisconnectWS : {}", event);
        log.info("user : {}", event.getUser());
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String username = sha.getSessionAttributes() != null ? (String) sha.getSessionAttributes().get("user") : null;
        log.info("username : {}", username);
        if (username != null) {
            presenceService.setUserOffline(username);
        } else {
            username = sessions.get(sha.getSessionId());
            log.info("username : {}", username);
            if(username != null) {
                presenceService.setUserOffline(username);
            }
        }
    }
}
