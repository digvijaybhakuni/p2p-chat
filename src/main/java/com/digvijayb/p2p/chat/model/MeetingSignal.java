package com.digvijayb.p2p.chat.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "meeting_signal")
public class MeetingSignal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String meetingId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 4096)
    private String offerSdp;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getMeetingId() { return meetingId; }
    public void setMeetingId(String meetingId) { this.meetingId = meetingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getOfferSdp() { return offerSdp; }
    public void setOfferSdp(String offerSdp) { this.offerSdp = offerSdp; }
}
