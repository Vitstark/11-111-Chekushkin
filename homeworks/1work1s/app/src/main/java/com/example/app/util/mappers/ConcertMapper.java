package com.example.app.util.mappers;

import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.app.models.Concert;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Vitaly Chekushkin
 */
public class ConcertMapper implements RowMapper<Concert> {
	@Override
	public Concert mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Concert.builder()
			.id(rs.getLong("id"))
			.title(rs.getString("title"))
			.author(rs.getString("author"))
			.description(rs.getString("description"))
			.imagePath(Path.of(rs.getString("image_path")))
			.build();
	}
}
