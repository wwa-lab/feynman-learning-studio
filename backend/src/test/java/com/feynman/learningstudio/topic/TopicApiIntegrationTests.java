package com.feynman.learningstudio.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feynman.learningstudio.topic.dto.CreateTopicRequest;
import com.feynman.learningstudio.topic.dto.UpdateTopicRequest;
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
class TopicApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void createsTopic() throws Exception {
        CreateTopicRequest request = new CreateTopicRequest(
                "Spring Boot Backend Basics",
                "spring-boot-backend-basics",
                "练习 Spring Boot backend CRUD 与本地验证。",
                true
        );

        mockMvc.perform(post("/api/topics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Spring Boot Backend Basics"))
                .andExpect(jsonPath("$.data.slug").value("spring-boot-backend-basics"))
                .andExpect(jsonPath("$.errorCode").doesNotExist())
                .andExpect(jsonPath("$.errorMessage").doesNotExist());
    }

    @Test
    void readsTopicById() throws Exception {
        long topicId = insertTopic("Legacy Modernization", "legacy-modernization");

        mockMvc.perform(get("/api/topics/{id}", topicId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(topicId))
                .andExpect(jsonPath("$.data.slug").value("legacy-modernization"));
    }

    @Test
    void listsTopicsWithPaginationEnvelope() throws Exception {
        insertTopic("Topic A", "topic-a");
        insertTopic("Topic B", "topic-b");

        mockMvc.perform(get("/api/topics")
                        .param("pageNo", "1")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.records.length()").value(2))
                .andExpect(jsonPath("$.data.pageNo").value(1))
                .andExpect(jsonPath("$.data.pageSize").value(10))
                .andExpect(jsonPath("$.data.total").value(2));
    }

    @Test
    void updatesTopic() throws Exception {
        long topicId = insertTopic("Old Name", "old-name");
        UpdateTopicRequest request = new UpdateTopicRequest(
                "New Name",
                "new-name",
                "更新后的说明。",
                false
        );

        mockMvc.perform(put("/api/topics/{id}", topicId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("New Name"))
                .andExpect(jsonPath("$.data.slug").value("new-name"))
                .andExpect(jsonPath("$.data.active").value(false));
    }

    @Test
    void deletesTopicWhenUnused() throws Exception {
        long topicId = insertTopic("Delete Me", "delete-me");

        mockMvc.perform(delete("/api/topics/{id}", topicId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        mockMvc.perform(get("/api/topics/{id}", topicId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("TOPIC_NOT_FOUND"));
    }

    @Test
    void rejectsDuplicateSlug() throws Exception {
        insertTopic("Existing", "existing");
        CreateTopicRequest request = new CreateTopicRequest("Duplicate", "existing", null, true);

        mockMvc.perform(post("/api/topics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("DUPLICATE_TOPIC_SLUG"));
    }

    @Test
    void rejectsInvalidSlug() throws Exception {
        CreateTopicRequest request = new CreateTopicRequest("Invalid", "Invalid Slug", null, true);

        mockMvc.perform(post("/api/topics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("VALIDATION_ERROR"));
    }

    @Test
    void returnsNotFoundForMissingTopic() throws Exception {
        mockMvc.perform(get("/api/topics/{id}", 99999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("TOPIC_NOT_FOUND"));
    }

    @Test
    void rejectsDeletingTopicReferencedByExperiment() throws Exception {
        long topicId = insertTopic("In Use", "in-use");
        insertExperiment(topicId);

        mockMvc.perform(delete("/api/topics/{id}", topicId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("TOPIC_IN_USE"));
    }

    private long insertTopic(String name, String slug) {
        jdbcTemplate.update("""
                insert into learning_topic (name, slug, description, active)
                values (?, ?, ?, true)
                """, name, slug, "test topic");
        return jdbcTemplate.queryForObject("select id from learning_topic where slug = ?", Long.class, slug);
    }

    private void insertExperiment(long topicId) {
        jdbcTemplate.update("""
                insert into learning_experiment (topic_id, title, summary, difficulty, status, spec_path)
                values (?, 'Referenced Experiment', 'summary', 'BEGINNER', 'DRAFT', null)
                """, topicId);
    }
}
