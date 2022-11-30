package com.example.app.util.exceptions;

public class NameValidationException extends ValidationException {
    public NameValidationException() {
        super();
    }

    public NameValidationException(String message) {
        super(message);
    }

    public NameValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NameValidationException(Throwable cause) {
        super(cause);
    }
}
