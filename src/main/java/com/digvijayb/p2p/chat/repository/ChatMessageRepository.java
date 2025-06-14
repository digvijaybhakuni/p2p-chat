package com.digvijayb.p2p.chat.repository;

import com.digvijayb.p2p.chat.model.ChatMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, UUID> {
    Page<ChatMessageEntity> findByChatPairIdOrderByTimestampAsc(String chatPairId, Pageable pageable);
}
