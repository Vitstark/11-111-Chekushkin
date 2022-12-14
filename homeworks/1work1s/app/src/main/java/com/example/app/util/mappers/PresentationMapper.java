package com.example.app.util.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.app.models.Presentation;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Vitaly Chekushkin
 */
public class PresentationMapper implements RowMapper<Presentation> {
	@Override
	public Presentation mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Presentation.builder()
			.id(rs.getLong("id"))
			.concertId(rs.getLong("concert_id"))
			.time(rs.getTimestamp("time"))
			.build();
	}
}
