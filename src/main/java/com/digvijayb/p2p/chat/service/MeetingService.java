package com.digvijayb.p2p.chat.service;

import com.digvijayb.p2p.chat.model.Meeting;
import com.digvijayb.p2p.chat.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;

    public Meeting createMeeting(String meetingName, String createdByName, String createdByEmail) {
        Meeting meeting = new Meeting();
        meeting.setId(UUID.randomUUID());
        meeting.setMeetingName(meetingName);
        meeting.setCreatedByName(createdByName);
        meeting.setCreatedByEmail(createdByEmail);
        meeting.setCreatedAt(Instant.now());
        meeting.setMeetingLink(generateMeetingLink());
        return meetingRepository.save(meeting);
    }

    private String generateMeetingLink() {
        return UUID.randomUUID().toString();
    }
}
