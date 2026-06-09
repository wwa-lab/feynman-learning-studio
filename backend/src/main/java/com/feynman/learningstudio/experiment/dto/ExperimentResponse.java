package com.feynman.learningstudio.experiment.dto;

import com.feynman.learningstudio.experiment.domain.LearningExperiment;

import java.time.LocalDateTime;

public record ExperimentResponse(
        Long id,
        Long topicId,
        String title,
        String summary,
        String difficulty,
        String status,
        String specPath,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ExperimentResponse from(LearningExperiment experiment) {
        return new ExperimentResponse(
                experiment.getId(),
                experiment.getTopicId(),
                experiment.getTitle(),
                experiment.getSummary(),
                experiment.getDifficulty(),
                experiment.getStatus(),
                experiment.getSpecPath(),
                experiment.getCreatedAt(),
                experiment.getUpdatedAt()
        );
    }
}
