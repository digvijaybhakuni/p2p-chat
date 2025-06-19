package com.digvijayb.p2p.chat.kafka;

import com.digvijayb.p2p.chat.model.ChatMessage;
import com.digvijayb.p2p.chat.model.ChatMessageEntity;
import com.digvijayb.p2p.chat.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageReceivedEventConsumer {
    @Autowired
    private PresenceService presenceService;
    // @Autowired
    // private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisTemplate<String, ChatMessageEntity> redisTemplate;

    @KafkaListener(topics = "message-received-event", groupId = "chat-group")
    public void handleMessage(ChatMessageEntity message) {
        boolean isOnline = presenceService.isUserOnline(message.getRecivier());
        if (isOnline) {
            redisTemplate.convertAndSend("chat", message);
            // messagingTemplate.convertAndSendToUser(message.getRecivier(), "/queue/messages", message);
        } else {
            // Sudo: NotificationService.sendPushNotification(message.getRecivier(), message.getContent());
            System.out.println("[SUDO] Send push notification to " + message.getRecivier() + ": " + message.getContent());
        }
    }
}
