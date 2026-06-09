package com.feynman.learningstudio.common.api;

import com.feynman.learningstudio.common.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTests {

    @Test
    void successWrapsDataAndClearsErrorFields() {
        ApiResponse<String> response = ApiResponse.success("ok");

        assertThat(response.success()).isTrue();
        assertThat(response.data()).isEqualTo("ok");
        assertThat(response.errorCode()).isNull();
        assertThat(response.errorMessage()).isNull();
    }

    @Test
    void failureClearsDataAndUsesStableErrorFields() {
        ApiResponse<Object> response = ApiResponse.failure(ErrorCode.VALIDATION_ERROR, "Request validation failed.");

        assertThat(response.success()).isFalse();
        assertThat(response.data()).isNull();
        assertThat(response.errorCode()).isEqualTo("VALIDATION_ERROR");
        assertThat(response.errorMessage()).isEqualTo("Request validation failed.");
    }

    @Test
    void pageResponseKeepsDeterministicPaginationShape() {
        PageResponse<String> page = PageResponse.of(List.of("a", "b"), 1, 10, 2);

        assertThat(page.records()).containsExactly("a", "b");
        assertThat(page.pageNo()).isEqualTo(1);
        assertThat(page.pageSize()).isEqualTo(10);
        assertThat(page.total()).isEqualTo(2);
    }
}
