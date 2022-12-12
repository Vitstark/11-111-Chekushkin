package com.example.app.util.validators;

import com.example.app.util.exceptions.NameValidationException;

public class NameValidator implements Validator<String> {
    private static final String NAME_PATTERN = "[A-ZА-Я][a-zа-я]*( [A-ZА-Я][a-zа-я]*)*";
    @Override
    public void validate(String name) throws NameValidationException {
        if (name == null || name.isBlank()) {
            throw new NameValidationException("Name can`t be empty");
        }

        if (!name.matches(NAME_PATTERN)) {
            throw new NameValidationException("Name must be correct, example: Chan Tien Dat");
        }
    }
}
