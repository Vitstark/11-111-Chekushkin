package com.example.app.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.app.models.Presentation;
import com.example.app.repositories.ConcertRepository;
import com.example.app.repositories.PresentationRepository;
import com.example.app.repositories.TicketRepository;
import com.example.app.util.exceptions.NotFoundException;

/**
 * @author Vitaly Chekushkin
 */
public class PresentationServiceImpl implements PresentationService{
	private final PresentationRepository presentationRepository;
	private final TicketRepository ticketRepository;
	private final ConcertRepository concertRepository;

	public PresentationServiceImpl(PresentationRepository presentationRepository,
		TicketRepository ticketRepository, ConcertRepository concertRepository) {
		this.presentationRepository = presentationRepository;
		this.ticketRepository = ticketRepository;
		this.concertRepository = concertRepository;
	}

	@Override
	public void save(Presentation presentation) {
		presentationRepository.save(presentation);
	}

	@Override
	public Presentation findById(Long id) {
		Presentation presentation = presentationRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Presentation not found!"));

		presentation.setConcert(concertRepository.findById(presentation.getConcertId()).get());
		presentation.setTickets(ticketRepository.findTicketsByPresentationId(presentation.getId()));

		return presentation;
	}

	@Override
	public List<Presentation> findAllOrderByDate() {
		return presentationRepository.findAll()
			.stream()
			.sorted(Comparator.comparing(Presentation::getPresentationTime))
			.collect(Collectors.toList());
	}

	@Override
	public void update(Presentation presentation) {
		presentationRepository.update(presentation);
	}

	@Override
	public void delete(Long presentationId) {
		presentationRepository.deleteById(presentationId);
	}
}
