package com.feynman.learningstudio.experiment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feynman.learningstudio.experiment.dto.CreateExperimentRequest;
import com.feynman.learningstudio.experiment.dto.UpdateExperimentRequest;
import com.feynman.learningstudio.experiment.dto.UpdateExperimentStatusRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(statements = {
        "delete from learning_experiment",
        "delete from learning_topic"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ExperimentApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void createsExperimentWithDefaultDraftStatus() throws Exception {
        long topicId = insertTopic("Backend", "backend");
        CreateExperimentRequest request = new CreateExperimentRequest(
                topicId,
                "实现 Topic API",
                "用 TDD 完成第一个 CRUD endpoint。",
                "BEGINNER",
                null,
                "docs/01-specs/v0.1-backend-learning-core.md"
        );

        mockMvc.perform(post("/api/experiments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.topicId").value(topicId))
                .andExpect(jsonPath("$.data.title").value("实现 Topic API"))
                .andExpect(jsonPath("$.data.status").value("DRAFT"));
    }

    @Test
    void readsExperimentById() throws Exception {
        long topicId = insertTopic("Backend", "backend");
        long experimentId = insertExperiment(topicId, "DRAFT");

        mockMvc.perform(get("/api/experiments/{id}", experimentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(experimentId))
                .andExpect(jsonPath("$.data.topicId").value(topicId));
    }

    @Test
    void listsExperimentsByTopic() throws Exception {
        long topicA = insertTopic("Topic A", "topic-a");
        long topicB = insertTopic("Topic B", "topic-b");
        insertExperiment(topicA, "DRAFT");
        insertExperiment(topicB, "DRAFT");

        mockMvc.perform(get("/api/experiments")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .param("topicId", String.valueOf(topicA)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.records.length()").value(1))
                .andExpect(jsonPath("$.data.records[0].topicId").value(topicA))
                .andExpect(jsonPath("$.data.total").value(1));
    }

    @Test
    void updatesExperiment() throws Exception {
        long topicId = insertTopic("Backend", "backend");
        long experimentId = insertExperiment(topicId, "DRAFT");
        UpdateExperimentRequest request = new UpdateExperimentRequest(
                topicId,
                "更新 Experiment",
                "更新后的 summary。",
                "INTERMEDIATE",
                "docs/01-specs/v0.1-backend-learning-core.md"
        );

        mockMvc.perform(put("/api/experiments/{id}", experimentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").value("更新 Experiment"))
                .andExpect(jsonPath("$.data.difficulty").value("INTERMEDIATE"));
    }

    @Test
    void deletesExperiment() throws Exception {
        long topicId = insertTopic("Backend", "backend");
        long experimentId = insertExperiment(topicId, "DRAFT");

        mockMvc.perform(delete("/api/experiments/{id}", experimentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        mockMvc.perform(get("/api/experiments/{id}", experimentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("EXPERIMENT_NOT_FOUND"));
    }

    @Test
    void rejectsInvalidTopicReference() throws Exception {
        CreateExperimentRequest request = new CreateExperimentRequest(
                99999L,
                "无效 Topic",
                null,
                "BEGINNER",
                null,
                null
        );

        mockMvc.perform(post("/api/experiments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("INVALID_TOPIC_REFERENCE"));
    }

    @Test
    void rejectsInvalidStatusValue() throws Exception {
        long topicId = insertTopic("Backend", "backend");
        CreateExperimentRequest request = new CreateExperimentRequest(topicId, "Invalid", null, "BEGINNER", "UNKNOWN", null);

        mockMvc.perform(post("/api/experiments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("INVALID_EXPERIMENT_STATUS"));
    }

    @Test
    void updatesStatusThroughAllowedTransition() throws Exception {
        long topicId = insertTopic("Backend", "backend");
        long experimentId = insertExperiment(topicId, "DRAFT");
        UpdateExperimentStatusRequest request = new UpdateExperimentStatusRequest("READY");

        mockMvc.perform(patch("/api/experiments/{id}/status", experimentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.status").value("READY"));
    }

    @Test
    void rejectsInvalidStatusTransition() throws Exception {
        long topicId = insertTopic("Backend", "backend");
        long experimentId = insertExperiment(topicId, "DRAFT");
        UpdateExperimentStatusRequest request = new UpdateExperimentStatusRequest("COMPLETED");

        mockMvc.perform(patch("/api/experiments/{id}/status", experimentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("INVALID_STATUS_TRANSITION"));
    }

    @Test
    void returnsNotFoundForMissingExperiment() throws Exception {
        mockMvc.perform(get("/api/experiments/{id}", 99999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("EXPERIMENT_NOT_FOUND"));
    }

    private long insertTopic(String name, String slug) {
        jdbcTemplate.update("""
                insert into learning_topic (name, slug, description, active)
                values (?, ?, ?, true)
                """, name, slug, "test topic");
        return jdbcTemplate.queryForObject("select id from learning_topic where slug = ?", Long.class, slug);
    }

    private long insertExperiment(long topicId, String status) {
        jdbcTemplate.update("""
                insert into learning_experiment (topic_id, title, summary, difficulty, status, spec_path)
                values (?, 'Experiment', 'summary', 'BEGINNER', ?, null)
                """, topicId, status);
        return jdbcTemplate.queryForObject("""
                select id
                from learning_experiment
                where topic_id = ?
                order by id desc
                limit 1
                """, Long.class, topicId);
    }
}
