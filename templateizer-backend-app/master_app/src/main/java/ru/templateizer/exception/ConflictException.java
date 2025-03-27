package ru.templateizer.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConflictException extends RuntimeException {

    private final String reason;
    private final LocalDateTime timestamp;

    public ConflictException(String message, String reason) {
        super(message);
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
    }
}