package com.feynman.learningstudio.experiment.domain;

import java.util.Arrays;

public enum ExperimentDifficulty {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED;

    public static ExperimentDifficulty from(String value) {
        return Arrays.stream(values())
                .filter(difficulty -> difficulty.name().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
