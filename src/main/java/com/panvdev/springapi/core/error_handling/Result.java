package com.panvdev.springapi.core.error_handling;

import java.util.List;

public record Result<T>(boolean isSuccess, T data, List<ApiError> errors){
    public static <T> Result<T> success(T data){
        return new Result<>(true, data, List.of());
    }

    public static <T> Result<T> failure(ApiError... errors){
        return new Result<>(false, null, List.of(errors));
    }
}
