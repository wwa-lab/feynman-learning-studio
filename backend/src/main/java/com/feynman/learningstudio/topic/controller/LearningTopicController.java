package com.feynman.learningstudio.topic.controller;

import com.feynman.learningstudio.common.api.ApiResponse;
import com.feynman.learningstudio.common.api.PageResponse;
import com.feynman.learningstudio.topic.dto.CreateTopicRequest;
import com.feynman.learningstudio.topic.dto.TopicResponse;
import com.feynman.learningstudio.topic.dto.UpdateTopicRequest;
import com.feynman.learningstudio.topic.service.LearningTopicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/topics")
public class LearningTopicController {

    private final LearningTopicService topicService;

    public LearningTopicController(LearningTopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ApiResponse<TopicResponse> create(@Valid @RequestBody CreateTopicRequest request) {
        return ApiResponse.success(topicService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<TopicResponse> getById(@PathVariable @Min(1) Long id) {
        return ApiResponse.success(topicService.getById(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<TopicResponse>> list(
            @RequestParam(defaultValue = "1") @Min(1) int pageNo,
            @RequestParam(defaultValue = "10") @Min(1) int pageSize
    ) {
        return ApiResponse.success(topicService.list(pageNo, pageSize));
    }

    @PutMapping("/{id}")
    public ApiResponse<TopicResponse> update(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody UpdateTopicRequest request
    ) {
        return ApiResponse.success(topicService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable @Min(1) Long id) {
        topicService.delete(id);
        return ApiResponse.success(null);
    }
}
