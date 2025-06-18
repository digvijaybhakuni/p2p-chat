package com.digvijayb.p2p.chat.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "chat_message")
public class ChatMessageEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "chat_pair_id", nullable = false)
    private String chatPairId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "recivier", nullable = false)
    private String recivier;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.PENDING;

    public enum Status {
        PENDING, DELIVERED, READ
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getChatPairId() { return chatPairId; }
    public void setChatPairId(String chatPairId) { this.chatPairId = chatPairId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getRecivier() { return recivier; }
    public void setRecivier(String recivier) { this.recivier = recivier; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
