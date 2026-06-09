package com.feynman.learningstudio.common.exception;

import com.feynman.learningstudio.common.api.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String VALIDATION_MESSAGE = "Request validation failed.";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException exception) {
        return ResponseEntity
                .status(httpStatusFor(exception.errorCode()))
                .body(ApiResponse.failure(exception.errorCode(), exception.getMessage()));
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BindException.class,
            ConstraintViolationException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ApiResponse<Void>> handleValidationException(Exception exception) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.failure(ErrorCode.VALIDATION_ERROR, VALIDATION_MESSAGE));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataAccessException(DataAccessException exception) {
        LOGGER.error("Database access failed. exceptionType={}", exception.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure(ErrorCode.DATABASE_ERROR, "Database access failed."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(Exception exception) {
        LOGGER.error("Unexpected system error. exceptionType={}", exception.getClass().getName());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.failure(ErrorCode.SYSTEM_ERROR, "Unexpected system error."));
    }

    private HttpStatus httpStatusFor(ErrorCode errorCode) {
        return switch (errorCode) {
            case TOPIC_NOT_FOUND, EXPERIMENT_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case DUPLICATE_TOPIC_SLUG, TOPIC_IN_USE, INVALID_STATUS_TRANSITION -> HttpStatus.CONFLICT;
            case VALIDATION_ERROR, INVALID_TOPIC_REFERENCE, INVALID_EXPERIMENT_STATUS -> HttpStatus.BAD_REQUEST;
            case DATABASE_ERROR, SYSTEM_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
