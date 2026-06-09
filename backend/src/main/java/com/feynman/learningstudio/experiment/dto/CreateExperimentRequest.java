package com.feynman.learningstudio.experiment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateExperimentRequest(
        @NotNull
        @Min(1)
        Long topicId,

        @NotBlank
        @Size(max = 160)
        String title,

        @Size(max = 1000)
        String summary,

        @NotBlank
        String difficulty,

        String status,

        @Size(max = 500)
        String specPath
) {
}
