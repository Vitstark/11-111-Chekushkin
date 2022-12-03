package com.example.app.service;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

import com.example.app.models.Person;
import com.example.app.models.Ticket;
import com.example.app.repositories.PeopleRepository;
import com.example.app.repositories.PresentationRepository;
import com.example.app.repositories.TicketRepository;
import com.example.app.util.exceptions.NotFoundException;

/**
 * @author Vitaly Chekushkin
 */
public class TicketServiceImpl implements TicketService {
	private final TicketRepository ticketRepository;
	private final PresentationRepository presentationRepository;
	private final PeopleRepository peopleRepository;

	public TicketServiceImpl(TicketRepository ticketRepository,
		PresentationRepository presentationRepository, PeopleRepository peopleRepository) {
		this.ticketRepository = ticketRepository;
		this.presentationRepository = presentationRepository;
		this.peopleRepository = peopleRepository;
	}

	@Override
	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	@Override
	public void update(Ticket ticket) {
		ticketRepository.update(ticket);
	}

	@Override
	public Ticket findByPk(Long presentationId, Integer row, Integer place) {
		Ticket ticket = ticketRepository.findTicketBy(presentationId, place, row)
			.orElseThrow(() -> new NotFoundException("Ticket not found!"));

		ticket.setPresentation(presentationRepository.findById(ticket.getPresentationId()).get());
		Optional<Person> person = peopleRepository.findById(ticket.getOwnerId());

		if (person.isPresent()) {
			ticket.setOwner(person.get());
		}

		return ticket;
	}

	@Override
	public void deleteByPk(Long presentationId, Integer row, Integer place) {
		ticketRepository.delete(presentationId, row, place);
	}

	@Override
	public void delete(Ticket ticket) {
		deleteByPk(ticket.getPresentationId(), ticket.getRow(), ticket.getPlace());
	}
}
