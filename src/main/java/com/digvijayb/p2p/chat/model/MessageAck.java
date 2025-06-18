package com.digvijayb.p2p.chat.model;

import java.util.UUID;

public class MessageAck {
    private UUID messageId;
    private String user;
    private String status; // DELIVERED or READ

    public UUID getMessageId() { return messageId; }
    public void setMessageId(UUID messageId) { this.messageId = messageId; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
