package com.example.app.service;

import java.util.List;

import com.example.app.models.Concert;
import com.example.app.repositories.ConcertRepository;
import com.example.app.repositories.PresentationRepository;
import com.example.app.util.exceptions.NotFoundException;

/**
 * @author Vitaly Chekushkin
 */
public class ConcertServiceImpl implements ConcertService {
	private final ConcertRepository concertRepository;
	private final PresentationRepository presentationRepository;

	public ConcertServiceImpl(ConcertRepository concertRepository,
		PresentationRepository presentationRepository) {
		this.concertRepository = concertRepository;
		this.presentationRepository = presentationRepository;
	}

	@Override
	public void save(Concert concert) {
		concertRepository.save(concert);
	}

	@Override
	public List<Concert> findAll() {
		List<Concert> concerts = concertRepository.findAll();

		concerts.forEach(concert -> concert.setPresentations(
			presentationRepository.findByConcert(concert.getId())));

		return concerts;
	}

	@Override
	public Concert findById(Long id) {
		Concert concert = concertRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Concert not found!"));

		concert.setPresentations(presentationRepository.findByConcert(concert.getId()));

		return concert;
	}

	@Override
	public void update(Concert concert) {
		concertRepository.update(concert);
		concert.setPresentations(presentationRepository.findByConcert(concert.getId()));
	}
}
