package com.digvijayb.p2p.chat.service;

import com.digvijayb.p2p.chat.model.ChatMessageEntity;
import com.digvijayb.p2p.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public ChatMessageEntity saveMessage(String sender, String recivier, String content) {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setId(UUID.randomUUID());
        entity.setSender(sender);
        entity.setRecivier(recivier);
        entity.setContent(content);
        entity.setTimestamp(Instant.now());
        entity.setChatPairId(getChatPairId(sender, recivier));
        entity.setStatus(ChatMessageEntity.Status.PENDING);
        ChatMessageEntity saved = chatMessageRepository.save(entity);
        kafkaTemplate.send("message-received-event", saved);
        return saved;
    }

    public void updateMessageStatus(UUID messageId, ChatMessageEntity.Status status) {
        chatMessageRepository.findById(messageId).ifPresent(msg -> {
            msg.setStatus(status);
            chatMessageRepository.save(msg);
        });
    }

    public Page<ChatMessageEntity> getMessages(String sender, String recivier, Pageable pageable) {
        String chatPairId = getChatPairId(sender, recivier);
        return chatMessageRepository.findByChatPairIdOrderByTimestampAsc(chatPairId, pageable);
    }

    private String getChatPairId(String sender, String recivier) {
        return sender.compareTo(recivier) < 0 ? sender + "_" + recivier : recivier + "_" + sender;
    }
}
