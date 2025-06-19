package com.digvijayb.p2p.chat.controller;

import com.digvijayb.p2p.chat.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PresenceController {
    @Autowired
    private PresenceService presenceService;

    @GetMapping("/api/presence")
    public Map<String, Object> isUserOnline(@RequestParam("username") String username) {
        boolean online = presenceService.isUserOnline(username);
        Map<String, Object> resp = new HashMap<>();
        resp.put("username", username);
        resp.put("online", online);
        return resp;
    }
}
