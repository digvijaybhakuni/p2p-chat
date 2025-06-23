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
        model.addAttribute("fbapiKey", System.getenv("fbapiKey"));
        model.addAttribute("fbauthDomain", System.getenv("fbauthDomain"));
        model.addAttribute("fbdatabaseURL", System.getenv("fbdatabaseURL"));
        model.addAttribute("fbprojectId", System.getenv("fbprojectId"));
        return "meeting/meeting";
    }
}
