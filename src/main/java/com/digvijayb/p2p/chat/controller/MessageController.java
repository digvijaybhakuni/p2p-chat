package com.digvijayb.p2p.chat.controller;

import com.digvijayb.p2p.chat.model.ChatMessage;
import com.digvijayb.p2p.chat.model.ChatMessageEntity;
import com.digvijayb.p2p.chat.service.MessageService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/api/messages")
    public Page<ChatMessageEntity> getMessages(
            @RequestParam("sender") String sender,
            @RequestParam("recivier") String recivier,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return messageService.getMessages(sender, recivier, pageable);
    }


    @PostMapping("/api/messages")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessage chatMessage) {
        log.info("chatMessage: {}", chatMessage);
        var res = messageService.saveMessage(chatMessage.getFrom(), 
                    chatMessage.getTo(), chatMessage.getContent());
        return ResponseEntity.ok(res);
    }
    
}
