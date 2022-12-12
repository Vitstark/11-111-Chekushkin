package com.example.app.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.app.models.Concert;
import com.example.app.util.exceptions.NotImplementedException;
import com.example.app.util.mappers.ConcertMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @author Vitaly Chekushkin
 */
public class ConcertRepositoryJdbcTemplateImpl implements ConcertRepository {
	//language=SQL
	private static final String SQL_FIND_ALL = "select * from concert";
	//language=SQL
	private static final String SQL_FIND_BY_ID = "select * from concert where id = :id";
	//language=SQL
	private static final String SQL_UPDATE_CONCERTO = "UPDATE concert SET title = :title, "
			+ "description = :description, author = :author, image_path = :image_path "
			+ "where id = :id";

	private static final RowMapper<Concert> concertoMapper = new ConcertMapper();

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public ConcertRepositoryJdbcTemplateImpl(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void save(Concert concerto) {
		Map<String, Object> mapParams = Map.of("title", concerto.getTitle(),
			"description", concerto.getDescription());

		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate());

		Long id = jdbcInsert.withTableName("concert")
			.usingGeneratedKeyColumns("id")
			.executeAndReturnKey(mapParams).longValue();

		concerto.setId(id);
	}

	@Override
	public List<Concert> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, concertoMapper);
	}

	@Override
	public Optional<Concert> findById(Long id) {
		Optional<Concert> objOptional;

		try {
			objOptional = Optional.ofNullable(jdbcTemplate.queryForObject(
				SQL_FIND_BY_ID, Map.of("id", id), concertoMapper));
		} catch (EmptyResultDataAccessException e) {
			objOptional = Optional.empty();
		}

		return objOptional;
	}

	@Override
	public void update(Concert concerto) {
		Map<String, Object> mapParams = Map.of("id", concerto.getId(),
			"title", concerto.getTitle(),
			"description", concerto.getDescription());


		jdbcTemplate.update(SQL_UPDATE_CONCERTO, mapParams);
	}

	@Override
	public void deleteById(Long id) {
		throw new NotImplementedException("Delete method not implemented");
	}
}
