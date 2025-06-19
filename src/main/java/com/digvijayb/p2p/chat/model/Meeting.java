package com.digvijayb.p2p.chat.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "meeting_name", nullable = false)
    private String meetingName;

    @Column(name = "meeting_link", nullable = false, unique = true)
    private String meetingLink;

    @Column(name = "created_by_name", nullable = false)
    private String createdByName;

    @Column(name = "created_by_email", nullable = false)
    private String createdByEmail;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getMeetingName() { return meetingName; }
    public void setMeetingName(String meetingName) { this.meetingName = meetingName; }
    public String getMeetingLink() { return meetingLink; }
    public void setMeetingLink(String meetingLink) { this.meetingLink = meetingLink; }
    public String getCreatedByName() { return createdByName; }
    public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }
    public String getCreatedByEmail() { return createdByEmail; }
    public void setCreatedByEmail(String createdByEmail) { this.createdByEmail = createdByEmail; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
