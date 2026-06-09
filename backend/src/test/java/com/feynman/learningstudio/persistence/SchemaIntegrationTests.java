package com.feynman.learningstudio.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SchemaIntegrationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void learningTopicTableExists() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from learning_topic", Integer.class);

        assertThat(count).isZero();
    }

    @Test
    void learningExperimentTableExists() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from learning_experiment", Integer.class);

        assertThat(count).isZero();
    }

    @Test
    void flywayAppliedInitialMigration() {
        Integer count = jdbcTemplate.queryForObject("""
                select count(*)
                from flyway_schema_history
                where version = '1'
                  and script = 'V1__create_learning_core_tables.sql'
                  and success = true
                """, Integer.class);

        assertThat(count).isOne();
    }
}
