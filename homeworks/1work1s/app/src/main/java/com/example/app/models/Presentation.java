package com.example.app.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Vitaly Chekushkin
 */
@AllArgsConstructor
@Builder
@Data
public class Presentation {
	private Long id;
	private Long concertId;
	private Timestamp time;

	@Transient
	private Concert concert;

	@Transient
	private List<Ticket> tickets;

	public void setConcert(Concert concert) {
		this.concert = concert;
		this.concertId = concert.getId();
	}
}
