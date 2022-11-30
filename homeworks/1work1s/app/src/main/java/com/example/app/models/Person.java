package com.example.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
