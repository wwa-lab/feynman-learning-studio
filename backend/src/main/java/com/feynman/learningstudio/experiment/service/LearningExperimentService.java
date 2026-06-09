package com.feynman.learningstudio.experiment.service;

import com.feynman.learningstudio.common.api.PageResponse;
import com.feynman.learningstudio.common.exception.BusinessException;
import com.feynman.learningstudio.common.exception.ErrorCode;
import com.feynman.learningstudio.experiment.domain.ExperimentDifficulty;
import com.feynman.learningstudio.experiment.domain.ExperimentStatus;
import com.feynman.learningstudio.experiment.domain.LearningExperiment;
import com.feynman.learningstudio.experiment.dto.CreateExperimentRequest;
import com.feynman.learningstudio.experiment.dto.ExperimentResponse;
import com.feynman.learningstudio.experiment.dto.UpdateExperimentRequest;
import com.feynman.learningstudio.experiment.mapper.LearningExperimentMapper;
import com.feynman.learningstudio.topic.mapper.LearningTopicMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LearningExperimentService {

    private static final int MAX_PAGE_SIZE = 100;
    private static final String DEFAULT_STATUS = ExperimentStatus.DRAFT.name();

    private final LearningExperimentMapper experimentMapper;
    private final LearningTopicMapper topicMapper;

    public LearningExperimentService(LearningExperimentMapper experimentMapper, LearningTopicMapper topicMapper) {
        this.experimentMapper = experimentMapper;
        this.topicMapper = topicMapper;
    }

    @Transactional
    public ExperimentResponse create(CreateExperimentRequest request) {
        ensureTopicExists(request.topicId());
        validateDifficulty(request.difficulty());
        String status = request.status() == null ? DEFAULT_STATUS : validateStatus(request.status()).name();
        LearningExperiment experiment = new LearningExperiment();
        experiment.setTopicId(request.topicId());
        experiment.setTitle(request.title());
        experiment.setSummary(request.summary());
        experiment.setDifficulty(request.difficulty());
        experiment.setStatus(status);
        experiment.setSpecPath(request.specPath());
        experimentMapper.insert(experiment);
        return ExperimentResponse.from(requireExperiment(experiment.getId()));
    }

    public ExperimentResponse getById(Long id) {
        return ExperimentResponse.from(requireExperiment(id));
    }

    public PageResponse<ExperimentResponse> list(int pageNo, int pageSize, Long topicId) {
        validatePagination(pageNo, pageSize);
        int offset = (pageNo - 1) * pageSize;
        List<ExperimentResponse> records = experimentMapper.findPage(topicId, pageSize, offset)
                .stream()
                .map(ExperimentResponse::from)
                .toList();
        return PageResponse.of(records, pageNo, pageSize, experimentMapper.countAll(topicId));
    }

    @Transactional
    public ExperimentResponse update(Long id, UpdateExperimentRequest request) {
        requireExperiment(id);
        ensureTopicExists(request.topicId());
        validateDifficulty(request.difficulty());
        LearningExperiment experiment = new LearningExperiment();
        experiment.setId(id);
        experiment.setTopicId(request.topicId());
        experiment.setTitle(request.title());
        experiment.setSummary(request.summary());
        experiment.setDifficulty(request.difficulty());
        experiment.setSpecPath(request.specPath());
        experimentMapper.update(experiment);
        return ExperimentResponse.from(requireExperiment(id));
    }

    @Transactional
    public ExperimentResponse updateStatus(Long id, String targetStatus) {
        LearningExperiment experiment = requireExperiment(id);
        ExperimentStatus current = validateStatus(experiment.getStatus());
        ExperimentStatus target = validateStatus(targetStatus);
        if (!current.canTransitionTo(target)) {
            throw new BusinessException(ErrorCode.INVALID_STATUS_TRANSITION, "Invalid experiment status transition.");
        }
        experimentMapper.updateStatus(id, target.name());
        return ExperimentResponse.from(requireExperiment(id));
    }

    @Transactional
    public void delete(Long id) {
        requireExperiment(id);
        experimentMapper.deleteById(id);
    }

    private LearningExperiment requireExperiment(Long id) {
        return experimentMapper.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXPERIMENT_NOT_FOUND, "Experiment not found."));
    }

    private void ensureTopicExists(Long topicId) {
        if (topicMapper.findById(topicId).isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_TOPIC_REFERENCE, "Experiment references a missing topic.");
        }
    }

    private void validateDifficulty(String difficulty) {
        try {
            ExperimentDifficulty.from(difficulty);
        } catch (IllegalArgumentException exception) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Invalid experiment difficulty.");
        }
    }

    private ExperimentStatus validateStatus(String status) {
        try {
            return ExperimentStatus.from(status);
        } catch (IllegalArgumentException exception) {
            throw new BusinessException(ErrorCode.INVALID_EXPERIMENT_STATUS, "Invalid experiment status.");
        }
    }

    private void validatePagination(int pageNo, int pageSize) {
        if (pageNo < 1 || pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Invalid pagination parameters.");
        }
    }
}
