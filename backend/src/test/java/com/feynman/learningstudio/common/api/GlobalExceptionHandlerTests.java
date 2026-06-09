package com.feynman.learningstudio.common.api;

import com.feynman.learningstudio.common.exception.BusinessException;
import com.feynman.learningstudio.common.exception.ErrorCode;
import com.feynman.learningstudio.common.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTests {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handlesBusinessExceptionWithMappedHttpStatus() {
        BusinessException exception = new BusinessException(ErrorCode.TOPIC_IN_USE, "Topic is used by experiments.");

        ResponseEntity<ApiResponse<Void>> response = handler.handleBusinessException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().success()).isFalse();
        assertThat(response.getBody().errorCode()).isEqualTo("TOPIC_IN_USE");
        assertThat(response.getBody().errorMessage()).isEqualTo("Topic is used by experiments.");
    }

    @Test
    void handlesUnexpectedExceptionWithoutLeakingInternalDetails() {
        RuntimeException exception = new RuntimeException("database password is secret");

        ResponseEntity<ApiResponse<Void>> response = handler.handleUnexpectedException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().success()).isFalse();
        assertThat(response.getBody().errorCode()).isEqualTo("SYSTEM_ERROR");
        assertThat(response.getBody().errorMessage()).isEqualTo("Unexpected system error.");
    }
}
