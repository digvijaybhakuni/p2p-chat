package com.digvijayb.p2p.chat.controller;

import com.digvijayb.p2p.chat.model.MessageAck;
import com.digvijayb.p2p.chat.model.ChatMessageEntity;
import com.digvijayb.p2p.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

@RestController
public class MessageAckController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/api/message/ack")
    public void ack(@RequestBody MessageAck ack) {
        // Update DB
        messageService.updateMessageStatus(ack.getMessageId(), ChatMessageEntity.Status.valueOf(ack.getStatus()));
        // Publish to Kafka
        kafkaTemplate.send("message-status-updates", ack);
    }
}
