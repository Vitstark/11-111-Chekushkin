package com.example.app.repositories;

import com.example.app.models.Person;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends CRUD<Person, Long> {
    Optional<Person> findByEmail(String email);
}
