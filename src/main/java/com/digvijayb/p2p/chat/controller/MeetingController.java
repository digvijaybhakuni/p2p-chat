package com.digvijayb.p2p.chat.controller;

import com.digvijayb.p2p.chat.model.Meeting;
import com.digvijayb.p2p.chat.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @PostMapping("/create")
    public Meeting createMeeting(@RequestParam("meetingName") String meetingName, 
                                @RequestParam("name") String name, 
                                @RequestParam("email") String email) {
        return meetingService.createMeeting(meetingName, name, email);
    }
}
