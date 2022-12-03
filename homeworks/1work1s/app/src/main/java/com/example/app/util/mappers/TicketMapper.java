package com.example.app.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.app.models.Ticket;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Vitaly Chekushkin
 */
public class TicketMapper implements RowMapper<Ticket> {
	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Ticket.builder()
			.presentationId(rs.getLong("presentation_id"))
			.row(rs.getInt("row"))
			.place(rs.getInt("place"))
			.ownerId(rs.getLong("owner_id"))
			.build();
	}
}
