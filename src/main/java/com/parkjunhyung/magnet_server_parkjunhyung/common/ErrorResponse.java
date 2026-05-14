package com.parkjunhyung.magnet_server_parkjunhyung.common;

public record ErrorResponse(String error) {

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code.getMessage());
    }
}
