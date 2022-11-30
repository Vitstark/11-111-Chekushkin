package com.example.app.util.validators;

import com.example.app.util.exceptions.ValidationException;

public interface Validator<T> {
    void validate(T validated) throws ValidationException;
}
