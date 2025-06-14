package com.digvijayb.p2p.chat.model;

import java.io.Serializable;

public class ChatMessage implements Serializable{
    private String from;
    private String to;
    private String content;

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
