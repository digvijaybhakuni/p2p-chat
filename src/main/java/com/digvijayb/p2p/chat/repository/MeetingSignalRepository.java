package com.digvijayb.p2p.chat.repository;

import com.digvijayb.p2p.chat.model.MeetingSignal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MeetingSignalRepository extends JpaRepository<MeetingSignal, UUID> {
    List<MeetingSignal> findByMeetingId(String meetingId);
    MeetingSignal findByMeetingIdAndUserId(String meetingId, String userId);
}
