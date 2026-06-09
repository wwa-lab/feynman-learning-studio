package com.feynman.learningstudio.topic.mapper;

import com.feynman.learningstudio.topic.domain.LearningTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface LearningTopicMapper {

    int insert(LearningTopic topic);

    Optional<LearningTopic> findById(@Param("id") Long id);

    int countAll();

    List<LearningTopic> findPage(@Param("limit") int limit, @Param("offset") int offset);

    boolean existsBySlug(@Param("slug") String slug);

    boolean existsBySlugExcludingId(@Param("slug") String slug, @Param("id") Long id);

    int update(LearningTopic topic);

    int deleteById(@Param("id") Long id);

    int countExperimentsByTopicId(@Param("topicId") Long topicId);
}
