package com.example.app.util.encoders;

public class PasswordHashCodeEncoder implements Encoder{
    @Override
    public CharSequence encode(CharSequence sequence) {
        int hash = sequence.hashCode();
        return Integer.toHexString(hash);
    }
}
