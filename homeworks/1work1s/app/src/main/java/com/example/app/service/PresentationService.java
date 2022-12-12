package com.example.app.service;

import java.util.List;

import com.example.app.models.Presentation;

/**
 * @author Vitaly Chekushkin
 */
public interface PresentationService {
	void save(Presentation presentation);
	Presentation findById(Long id);
	List<Presentation> findAllOrderByDate();
	List<Presentation> findAllByConcertId(Long concertId);
	void update(Presentation presentation);
	void delete(Long presentationId);
}
