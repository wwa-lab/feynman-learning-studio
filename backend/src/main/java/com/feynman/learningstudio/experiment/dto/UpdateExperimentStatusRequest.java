package com.feynman.learningstudio.experiment.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateExperimentStatusRequest(
        @NotBlank
        String status
) {
}
