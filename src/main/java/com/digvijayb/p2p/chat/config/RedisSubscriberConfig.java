package com.digvijayb.p2p.chat.config;

import com.digvijayb.p2p.chat.service.RedisMessageSubscriber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisSubscriberConfig {

    @Autowired
    private RedisMessageSubscriber subscriber;

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(subscriber, 
            List.of(new PatternTopic("chat"),
            new PatternTopic("ack")));
        return container;
    }
    // @Bean
    // public RedisMessageListenerContainer redisContainerAck(RedisConnectionFactory connectionFactory) {
    //     RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    //     container.setConnectionFactory(connectionFactory);
    //     container.addMessageListener(subscriber, new PatternTopic("ack"));
    //     return container;
    // }
}
