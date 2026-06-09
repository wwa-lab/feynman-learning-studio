package com.feynman.learningstudio.common.api;

import java.util.List;

public record PageResponse<T>(
        List<T> records,
        int pageNo,
        int pageSize,
        long total
) {

    public static <T> PageResponse<T> of(List<T> records, int pageNo, int pageSize, long total) {
        return new PageResponse<>(List.copyOf(records), pageNo, pageSize, total);
    }
}
