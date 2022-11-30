package com.example.app.service;

import com.example.app.models.Person;
import com.example.app.repositories.PeopleRepository;
import com.example.app.util.encoders.Encoder;

import java.util.List;
import java.util.Optional;

public class PeopleServiceImpl implements PeopleService {
    private final PeopleRepository peopleRepository;
    private final Encoder passwordEncoder;

    public PeopleServiceImpl(PeopleRepository peopleRepository, Encoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(Person person) {
        CharSequence password = person.getPassword();
        String encodedPassword = (String) passwordEncoder.encode(password);
        person.setPassword(encodedPassword);
        peopleRepository.save(person);
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return peopleRepository.findById(id);
    }

    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    @Override
    public void update(Person person) {
        peopleRepository.update(person);
    }

    @Override
    public void deleteById(Long id) {
        peopleRepository.deleteById(id);
    }
}
