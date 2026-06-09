package com.feynman.learningstudio.experiment.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public enum ExperimentStatus {
    DRAFT,
    READY,
    RUNNING,
    COMPLETED,
    ARCHIVED;

    private static final Map<ExperimentStatus, Set<ExperimentStatus>> ALLOWED_TRANSITIONS = Map.of(
            DRAFT, Set.of(READY, ARCHIVED),
            READY, Set.of(RUNNING, ARCHIVED),
            RUNNING, Set.of(COMPLETED, ARCHIVED),
            COMPLETED, Set.of(),
            ARCHIVED, Set.of()
    );

    public static ExperimentStatus from(String value) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean canTransitionTo(ExperimentStatus target) {
        return ALLOWED_TRANSITIONS.get(this).contains(target);
    }
}
