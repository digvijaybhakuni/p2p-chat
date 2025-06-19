package com.digvijayb.p2p.chat.controller;

import com.digvijayb.p2p.chat.model.MeetingSignal;
import com.digvijayb.p2p.chat.repository.MeetingSignalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meeting/signal")
public class MeetingSignalController {
    @Autowired
    private MeetingSignalRepository meetingSignalRepository;

    @PostMapping("/offer")
    public MeetingSignal saveOffer(@RequestParam("meetingId") String meetingId, @RequestParam("userId") String userId, @RequestBody String offerSdp) {
        MeetingSignal signal = new MeetingSignal();
        signal.setMeetingId(meetingId);
        signal.setUserId(userId);
        signal.setOfferSdp(offerSdp);
        return meetingSignalRepository.save(signal);
    }

    @GetMapping("/offer")
    public MeetingSignal getOffer(@RequestParam("meetingId") String meetingId, @RequestParam("userId") String userId) {
        return meetingSignalRepository.findByMeetingIdAndUserId(meetingId, userId);
    }
}
