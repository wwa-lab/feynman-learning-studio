package com.feynman.learningstudio.topic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateTopicRequest(
        @NotBlank
        @Size(max = 100)
        String name,

        @NotBlank
        @Size(max = 80)
        @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$")
        String slug,

        @Size(max = 1000)
        String description,

        @NotNull
        Boolean active
) {
}
