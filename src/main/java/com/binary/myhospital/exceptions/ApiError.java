package com.binary.myhospital.exceptions;

import org.springframework.http.HttpStatus;

public record ApiError(String message, int httpStatus, String path, java.time.LocalDateTime timestamp) {
}
