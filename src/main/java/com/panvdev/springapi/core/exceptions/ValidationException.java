package com.panvdev.springapi.core.exceptions;

import com.panvdev.springapi.core.error_handling.ApiError;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends  RuntimeException{
    private final List<ApiError> errors;

    public ValidationException(List<ApiError> errors){
        super("Validation failed");
        this.errors = errors;
    }
}
