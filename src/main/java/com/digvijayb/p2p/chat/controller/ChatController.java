package com.digvijayb.p2p.chat.controller;

import com.digvijayb.p2p.chat.model.ChatMessage;
import com.digvijayb.p2p.chat.model.MessageAck;
import com.digvijayb.p2p.chat.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MessageMapping("/chat")
    public String send(ChatMessage message) throws Exception {
        // Save message and trigger Kafka event
        var messageSaved = messageService.saveMessage(message.getFrom(), message.getTo(), message.getContent());
        return messageSaved.getId().toString();
    }

    @MessageMapping("/ack")
    public void ack(MessageAck ack) {

        log.info("Ack : {}", ack);
        // Publish to Kafka for status update
        kafkaTemplate.send("message-status-updates", ack);
    }
}
