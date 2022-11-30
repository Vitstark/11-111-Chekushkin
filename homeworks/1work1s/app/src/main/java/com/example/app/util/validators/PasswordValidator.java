package com.example.app.util.validators;

import com.example.app.util.exceptions.PasswordValidationException;

public class PasswordValidator implements Validator<CharSequence>{
    private final static int minLength = 6;
    @Override
    public void validate(CharSequence password) throws PasswordValidationException {
        if (password == null || password.length() < minLength) {
            throw new PasswordValidationException(String.format("Password must have more than %s symbols", minLength - 1));
        }
    }
}
