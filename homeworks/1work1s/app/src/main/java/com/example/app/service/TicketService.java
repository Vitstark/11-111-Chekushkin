package com.example.app.service;

import java.util.List;

import com.example.app.models.Ticket;

/**
 * @author Vitaly Chekushkin
 */
public interface TicketService {
	void save(Ticket ticket);
	void update(Ticket ticket);
	Ticket findByPk(Long presentationId, Integer row, Integer place);
	void deleteByPk(Long presentationId, Integer row, Integer place);
	void delete(Ticket ticket);
	List<Ticket> findAllByPresentationIdAndRow(Long presentationId, Integer row);
}
