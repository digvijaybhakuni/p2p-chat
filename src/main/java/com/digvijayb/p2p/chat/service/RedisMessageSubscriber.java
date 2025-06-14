package com.digvijayb.p2p.chat.service;

import com.digvijayb.p2p.chat.model.ChatMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisMessageSubscriber implements MessageListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            // Print raw bytes for debugging
            System.out.println("Raw bytes: " + java.util.Arrays.toString(message.getBody()));
            String json = new String(message.getBody(), java.nio.charset.StandardCharsets.UTF_8);
            System.out.println("Decoded string: " + json);


            
            ChatMessage chatMessage = getChatMessage(message); //objectMapper.readValue(json, ChatMessage.class);
            System.out.println(chatMessage);
            messagingTemplate.convertAndSendToUser(
                chatMessage.getTo(), "/queue/messages", chatMessage
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ChatMessage getChatMessage(Message message) {
        byte[] serializedData = message.getBody();
        
        try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            
            ChatMessage deserializedObject = (ChatMessage) ois.readObject();
            System.out.println("Deserialized object: " + deserializedObject);
            return deserializedObject;
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
