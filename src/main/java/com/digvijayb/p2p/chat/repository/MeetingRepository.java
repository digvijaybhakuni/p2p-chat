package com.digvijayb.p2p.chat.repository;

import com.digvijayb.p2p.chat.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
    Meeting findByMeetingLink(String meetingLink);
}
