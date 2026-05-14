package com.parkjunhyung.magnet_server_parkjunhyung.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MagnetException.class)
    public ResponseEntity<ErrorResponse> handleMagnetException(MagnetException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(ErrorResponse.of(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        boolean isLetterTooLong = ex.getBindingResult().getFieldErrors().stream()
                .anyMatch(e -> e.getField().contains("letter") && "Size".equals(e.getCode()));

        ErrorCode errorCode = isLetterTooLong ? ErrorCode.LETTER_TOO_LONG : ErrorCode.INVALID_INPUT_VALUE;
        return ResponseEntity.status(errorCode.getStatus()).body(ErrorResponse.of(errorCode));
    }
}
