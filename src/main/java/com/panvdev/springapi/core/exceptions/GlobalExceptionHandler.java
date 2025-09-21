package com.panvdev.springapi.core.exceptions;

import com.panvdev.springapi.core.error_handling.ApiError;
import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.core.storage.StorageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result<?>> handleNotFound(NotFoundException exception){
        ApiError error = new ApiError("NOT_FOUND", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Result.failure(error));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Result<?>> handleValidation(ValidationException exception){
        ApiError error = new ApiError("VALIDATION_ERROR", exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(Result.failure(exception.getErrors().toArray(ApiError[]::new)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<?>> handleGeneral(Exception exception){
        ApiError error = new ApiError("INTERNAL_ERROR", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.failure(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<ApiError> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ApiError(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(Result.failure(validationErrors.toArray(ApiError[]::new)));
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<Result<?>> handleStorage(StorageException exception){
        ApiError error = new ApiError("STORAGE_ERROR", exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(Result.failure(error));
    }
}
