package util.exceptions;

public class PasswordValidationException extends ValidationException {
    public PasswordValidationException(String message) {
        super(message);
    }

    public PasswordValidationException() {
        super();
    }

    public PasswordValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordValidationException(Throwable cause) {
        super(cause);
    }
}
