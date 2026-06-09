package com.feynman.learningstudio.topic.dto;

import com.feynman.learningstudio.topic.domain.LearningTopic;

import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String name,
        String slug,
        String description,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static TopicResponse from(LearningTopic topic) {
        return new TopicResponse(
                topic.getId(),
                topic.getName(),
                topic.getSlug(),
                topic.getDescription(),
                topic.getActive(),
                topic.getCreatedAt(),
                topic.getUpdatedAt()
        );
    }
}
