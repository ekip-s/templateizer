package ru.templateizer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {

    private final String reason;
    private final LocalDateTime timestamp;

    public NotFoundException(String message, String reason) {
        super(message);
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
    }
}