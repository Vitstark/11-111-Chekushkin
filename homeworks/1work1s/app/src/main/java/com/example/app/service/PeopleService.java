package com.example.app.service;

import com.example.app.models.Person;

import java.util.List;
import java.util.Optional;

public interface PeopleService {
    void save(Person person);
    List<Person> findAll();
    Person findById(Long id);
    Optional<Person> findByEmail(String email);
    void update(Person person);
    void deleteById(Long id);
}
