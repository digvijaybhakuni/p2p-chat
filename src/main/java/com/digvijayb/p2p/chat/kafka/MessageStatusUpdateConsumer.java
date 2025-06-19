package com.digvijayb.p2p.chat.kafka;

import com.digvijayb.p2p.chat.model.MessageAck;
import com.digvijayb.p2p.chat.model.ChatMessageEntity;
import com.digvijayb.p2p.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageStatusUpdateConsumer {
    @Autowired
    private MessageService messageService;
    // @Autowired
    // private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisTemplate<String, MessageAck> redisTemplate;

    @KafkaListener(topics = "message-status-updates", groupId = "chat-group")
    public void handleStatusUpdate(MessageAck ack) {
        // Update DB
        messageService.updateMessageStatus(ack.getMessageId(), ChatMessageEntity.Status.valueOf(ack.getStatus()));
        // Notify sender via WS
        redisTemplate.convertAndSend("ack", ack);
        // messagingTemplate.convertAndSendToUser(
        //     ack.getUser(), "/queue/message-status", ack
        // );
    }
}
