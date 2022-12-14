package com.example.app.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.app.models.Presentation;

/**
 * @author Vitaly Chekushkin
 */
public interface PresentationService {
	void save(Presentation presentation);
	Presentation findById(Long id);
	List<Presentation> findAllByConcertId(Long concertId);
	List<Presentation> findByConcertAndDateOrderByDate(Long concertId, LocalDate date);
	void update(Presentation presentation);
	void delete(Long presentationId);
}
