package com.feynman.learningstudio.experiment.controller;

import com.feynman.learningstudio.common.api.ApiResponse;
import com.feynman.learningstudio.common.api.PageResponse;
import com.feynman.learningstudio.experiment.dto.CreateExperimentRequest;
import com.feynman.learningstudio.experiment.dto.ExperimentResponse;
import com.feynman.learningstudio.experiment.dto.UpdateExperimentRequest;
import com.feynman.learningstudio.experiment.dto.UpdateExperimentStatusRequest;
import com.feynman.learningstudio.experiment.service.LearningExperimentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/experiments")
public class LearningExperimentController {

    private final LearningExperimentService experimentService;

    public LearningExperimentController(LearningExperimentService experimentService) {
        this.experimentService = experimentService;
    }

    @PostMapping
    public ApiResponse<ExperimentResponse> create(@Valid @RequestBody CreateExperimentRequest request) {
        return ApiResponse.success(experimentService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<ExperimentResponse> getById(@PathVariable @Min(1) Long id) {
        return ApiResponse.success(experimentService.getById(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<ExperimentResponse>> list(
            @RequestParam(defaultValue = "1") @Min(1) int pageNo,
            @RequestParam(defaultValue = "10") @Min(1) int pageSize,
            @RequestParam(required = false) @Min(1) Long topicId
    ) {
        return ApiResponse.success(experimentService.list(pageNo, pageSize, topicId));
    }

    @PutMapping("/{id}")
    public ApiResponse<ExperimentResponse> update(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody UpdateExperimentRequest request
    ) {
        return ApiResponse.success(experimentService.update(id, request));
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<ExperimentResponse> updateStatus(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody UpdateExperimentStatusRequest request
    ) {
        return ApiResponse.success(experimentService.updateStatus(id, request.status()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable @Min(1) Long id) {
        experimentService.delete(id);
        return ApiResponse.success(null);
    }
}
