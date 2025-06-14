package com.digvijayb.p2p.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PresenceService {
    private static final String ONLINE_KEY = "presence:online:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setUserOnline(String username) {
        redisTemplate.opsForValue().set(ONLINE_KEY + username, "1");
    }

    public void setUserOffline(String username) {
        redisTemplate.delete(ONLINE_KEY + username);
    }

    public boolean isUserOnline(String username) {
        return redisTemplate.hasKey(ONLINE_KEY + username);
    }
}
