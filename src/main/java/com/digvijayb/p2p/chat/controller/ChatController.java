package com.digvijayb.p2p.chat.controller;

import com.digvijayb.p2p.chat.model.ChatMessage;
import com.digvijayb.p2p.chat.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MessageMapping("/chat")
    public void send(ChatMessage message) throws Exception {
        // Save message and trigger Kafka event
        messageService.saveMessage(message.getFrom(), message.getTo(), message.getContent());
    }
}
