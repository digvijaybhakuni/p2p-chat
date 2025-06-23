package com.digvijayb.p2p.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MeetingPageController {
    @GetMapping("/meeting/{meetingId}")
    public String meetingPage(@PathVariable("meetingId") String meetingId, Model model) {
        log.info("meetingId: {}", meetingId);
        model.addAttribute("meetingId", meetingId);
        model.addAttribute("fb_apiKey", System.getenv("fb_apiKey"));
        model.addAttribute("fb_authDomain", System.getenv("fb_authDomain"));
        model.addAttribute("fb_databaseURL", System.getenv("fb_databaseURL"));
        model.addAttribute("fb_projectId", System.getenv("fb_projectId"));
        return "meeting/meeting";
    }
}
