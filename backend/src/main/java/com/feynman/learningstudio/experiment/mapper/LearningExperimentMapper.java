package com.feynman.learningstudio.experiment.mapper;

import com.feynman.learningstudio.experiment.domain.LearningExperiment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface LearningExperimentMapper {

    int insert(LearningExperiment experiment);

    Optional<LearningExperiment> findById(@Param("id") Long id);

    int countAll(@Param("topicId") Long topicId);

    List<LearningExperiment> findPage(
            @Param("topicId") Long topicId,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    int update(LearningExperiment experiment);

    int updateStatus(@Param("id") Long id, @Param("status") String status);

    int deleteById(@Param("id") Long id);
}
