package com.example.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Transient;

@Data
@Builder
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

    @Transient
    private List<Ticket> tickets;
}
