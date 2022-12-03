package com.example.app.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.app.models.Concert;
import com.example.app.models.Presentation;
import com.example.app.util.mappers.PresentationMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @author Vitaly Chekushkin
 */
public class PresentationRepositoryJdbcTemplateImpl implements PresentationRepository{
	//language=SQL
	private static final String SQL_FIND_ALL = "select * from presentation";
	//language=SQL
	private static final String SQL_FIND_BY_ID = "select * from presentation where id = :id";
	//language=SQL
	private static final String SQL_UPDATE_PRESENTATION = "update presentation "
				+ "set presentation_time = :presentation_time, concert_id = :concert_id "
				+ "where id = :id";
	//language=SQL
	private static final String SQL_DELETE_PRESENTATION = "delete from presentation where id = :id";
	//language=SQL
	private static final String SQL_FIND_BY_CONCERTO_ID = "select * from presentation where concert_id = :concert_id";

	private static final RowMapper<Presentation> presentationMapper = new PresentationMapper();

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public PresentationRepositoryJdbcTemplateImpl(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void save(Presentation presentation) {
		Map<String, Object> paramsMap = Map.of(
			"presentation_time", presentation.getPresentationTime(),
			"concert_id", presentation.getConcertId());

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate());

		Long id = simpleJdbcInsert.withTableName("presentation")
			.usingGeneratedKeyColumns("id")
			.executeAndReturnKey(paramsMap).longValue();

		presentation.setId(id);
	}

	@Override
	public List<Presentation> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, presentationMapper);
	}

	@Override
	public Optional<Presentation> findById(Long id) {
		Optional<Presentation> presentationOptional;

		try {
			presentationOptional = Optional.ofNullable(
				jdbcTemplate.queryForObject(SQL_FIND_BY_ID, Map.of("id", id), presentationMapper));
		} catch (EmptyResultDataAccessException e) {
			presentationOptional = Optional.empty();
		}

		return presentationOptional;
	}

	@Override
	public void update(Presentation presentation) {
		Map<String, Object> paramsMap = Map.of(
			"id", presentation.getId(),
			"presentation_time", presentation.getPresentationTime(),
			"concert_id", presentation.getConcertId());

		jdbcTemplate.update(SQL_UPDATE_PRESENTATION, paramsMap);
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(SQL_DELETE_PRESENTATION, Map.of("id", id));
	}

	@Override
	public List<Presentation> findByConcert(Long concertId) {
		return jdbcTemplate.query(SQL_FIND_BY_CONCERTO_ID,
			Map.of("concert_id", concertId), presentationMapper);
	}
}
