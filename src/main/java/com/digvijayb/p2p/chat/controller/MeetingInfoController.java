package com.digvijayb.p2p.chat.controller;

import com.digvijayb.p2p.chat.model.Meeting;
import com.digvijayb.p2p.chat.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingInfoController {
    @Autowired
    private MeetingRepository meetingRepository;

    @GetMapping("/api/meetings/info")
    public Meeting getMeetingInfo(@RequestParam("meetingLink") String meetingLink) {
        return meetingRepository.findByMeetingLink(meetingLink);
    }
}
