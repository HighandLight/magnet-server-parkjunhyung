package com.parkjunhyung.magnet_server_parkjunhyung.common;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 4XX
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "유효하지 않은 입력값입니다."),
    LETTER_TOO_LONG(HttpStatus.BAD_REQUEST, "자석 길이가 너무 깁니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() { return status; }
    public String getMessage() { return message; }
}
