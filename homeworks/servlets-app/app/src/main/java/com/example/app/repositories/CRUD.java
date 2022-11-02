package com.example.app.repositories;

import java.util.List;
import java.util.Optional;

public interface CRUD<E,ID> {
    void save(E entity);
    List<E> findAll();
    Optional<E> findById(ID id);
    void update(E entity);
    void deleteById(ID id);
}
