package com.feynman.learningstudio.topic.service;

import com.feynman.learningstudio.common.api.PageResponse;
import com.feynman.learningstudio.common.exception.BusinessException;
import com.feynman.learningstudio.common.exception.ErrorCode;
import com.feynman.learningstudio.topic.domain.LearningTopic;
import com.feynman.learningstudio.topic.dto.CreateTopicRequest;
import com.feynman.learningstudio.topic.dto.TopicResponse;
import com.feynman.learningstudio.topic.dto.UpdateTopicRequest;
import com.feynman.learningstudio.topic.mapper.LearningTopicMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LearningTopicService {

    private static final int MAX_PAGE_SIZE = 100;

    private final LearningTopicMapper topicMapper;

    public LearningTopicService(LearningTopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Transactional
    public TopicResponse create(CreateTopicRequest request) {
        ensureSlugAvailable(request.slug());
        LearningTopic topic = new LearningTopic();
        topic.setName(request.name());
        topic.setSlug(request.slug());
        topic.setDescription(request.description());
        topic.setActive(request.active() == null || request.active());
        topicMapper.insert(topic);
        return TopicResponse.from(requireTopic(topic.getId()));
    }

    public TopicResponse getById(Long id) {
        return TopicResponse.from(requireTopic(id));
    }

    public PageResponse<TopicResponse> list(int pageNo, int pageSize) {
        validatePagination(pageNo, pageSize);
        int offset = (pageNo - 1) * pageSize;
        List<TopicResponse> records = topicMapper.findPage(pageSize, offset)
                .stream()
                .map(TopicResponse::from)
                .toList();
        return PageResponse.of(records, pageNo, pageSize, topicMapper.countAll());
    }

    @Transactional
    public TopicResponse update(Long id, UpdateTopicRequest request) {
        requireTopic(id);
        ensureSlugAvailableForUpdate(request.slug(), id);
        LearningTopic topic = new LearningTopic();
        topic.setId(id);
        topic.setName(request.name());
        topic.setSlug(request.slug());
        topic.setDescription(request.description());
        topic.setActive(request.active());
        topicMapper.update(topic);
        return TopicResponse.from(requireTopic(id));
    }

    @Transactional
    public void delete(Long id) {
        requireTopic(id);
        if (topicMapper.countExperimentsByTopicId(id) > 0) {
            throw new BusinessException(ErrorCode.TOPIC_IN_USE, "Topic is used by experiments.");
        }
        topicMapper.deleteById(id);
    }

    private LearningTopic requireTopic(Long id) {
        return topicMapper.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOPIC_NOT_FOUND, "Topic not found."));
    }

    private void ensureSlugAvailable(String slug) {
        if (topicMapper.existsBySlug(slug)) {
            throw new BusinessException(ErrorCode.DUPLICATE_TOPIC_SLUG, "Topic slug already exists.");
        }
    }

    private void ensureSlugAvailableForUpdate(String slug, Long id) {
        if (topicMapper.existsBySlugExcludingId(slug, id)) {
            throw new BusinessException(ErrorCode.DUPLICATE_TOPIC_SLUG, "Topic slug already exists.");
        }
    }

    private void validatePagination(int pageNo, int pageSize) {
        if (pageNo < 1 || pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Invalid pagination parameters.");
        }
    }
}
