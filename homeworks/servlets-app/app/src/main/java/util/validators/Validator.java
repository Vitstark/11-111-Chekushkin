package util.validators;

import util.exceptions.ValidationException;

public interface Validator<T> {
    void validate(T validated) throws ValidationException;
}
