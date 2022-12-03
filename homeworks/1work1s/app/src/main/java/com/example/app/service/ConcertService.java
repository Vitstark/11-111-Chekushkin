package com.example.app.service;

import java.util.List;

import com.example.app.models.Concert;

/**
 * @author Vitaly Chekushkin
 */
public interface ConcertService {
	void save(Concert concert);
	List<Concert> findAll();
	Concert findById(Long id);
	void update(Concert concert);
}
