package com.feynman.learningstudio.common.api;

import com.feynman.learningstudio.common.exception.ErrorCode;

public record ApiResponse<T>(
        boolean success,
        T data,
        String errorCode,
        String errorMessage
) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, null);
    }

    public static <T> ApiResponse<T> failure(ErrorCode errorCode, String errorMessage) {
        return new ApiResponse<>(false, null, errorCode.name(), errorMessage);
    }
}
