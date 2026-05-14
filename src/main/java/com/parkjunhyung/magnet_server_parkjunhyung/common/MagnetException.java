package com.parkjunhyung.magnet_server_parkjunhyung.common;

public class MagnetException extends RuntimeException {

    private final ErrorCode errorCode;

    public MagnetException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() { return errorCode; }
}
