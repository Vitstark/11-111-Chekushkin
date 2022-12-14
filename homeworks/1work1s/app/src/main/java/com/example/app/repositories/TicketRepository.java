package com.example.app.repositories;

import java.util.List;
import java.util.Optional;

import com.example.app.models.Ticket;

/**
 * @author Vitaly Chekushkin
 */
public interface TicketRepository {
	void save(Ticket ticket);
	Optional<Ticket> findTicketBy(Long presentationId, Integer place, Integer row);
	List<Ticket> findTicketsByPresentationId(Long presentationId);
	List<Ticket> findTicketsByPersonId(Long personId);
	List<Ticket> findTicketsByPresentationIdAndByRowOrderByPlace(Long presentationId, Integer row);
	void update(Ticket ticket);
	void delete(Long presentationId, Integer row, Integer place);
}
