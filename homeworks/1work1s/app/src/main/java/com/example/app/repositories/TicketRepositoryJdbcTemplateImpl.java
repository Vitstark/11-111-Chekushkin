package com.example.app.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.app.models.Presentation;
import com.example.app.models.Ticket;
import com.example.app.util.mappers.TicketMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @author Vitaly Chekushkin
 */
public class TicketRepositoryJdbcTemplateImpl implements TicketRepository {
	//language=SQL
	private static final String SQL_FIND_BY_PK = "select * from ticket "
												 + "where presentation_id = :presentation_id and "
												 + "row = :row and place = :place";
	//language=SQL
	private static final String SQL_FIND_BY_PRESENTATION_ID = "select * from ticket "
															  + "where presentation_id = :presentation_id";
	//language=SQL
	private static final String SQL_FIND_BY_OWNER_ID = "select * from ticket "
													   + "where owner_id = :owner_id";
	//language=SQL
	private static final String SQL_UPDATE_TICKET = "update ticket set owner_id = :owner_id "
													+ "where presentation_id = :presentation_id and "
													+ "row = :row and place = :place";
	//language=SQL
	private static final String SQL_DELETE_TICKET = "delete from ticket "
													+ "where presentation_id = :presentation_id and "
													+ "row = :row and place = :place";
	//language=SQL
	private static final String SQL_FIND_BY_PRESENTATION_AND_ROW =
		"select * from ticket where presentation_id = :presentation_id and row = :row and owner_id is null "
		+ "order by place";

	private static final RowMapper<Ticket> ticketMapper = new TicketMapper();

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public TicketRepositoryJdbcTemplateImpl(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void save(Ticket ticket) {
		Map<String, Object> paramsMap = Map.of(
			"presentation_id", ticket.getPresentationId(),
			"row", ticket.getRow(),
			"place", ticket.getPlace(),
			"owner_id", ticket.getOwnerId());

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate
			.getJdbcTemplate());

		simpleJdbcInsert.withTableName("ticket")
			.execute(paramsMap);
	}

	@Override
	public Optional<Ticket> findTicketBy(Long presentationId,
		Integer place, Integer row) {
		Optional<Ticket> ticketOptional;

		Map<String, Object> paramsMap = Map.of(
			"presentation_id", presentationId,
			"row", row,
			"place", place);

		try {
			ticketOptional = Optional.ofNullable(
				jdbcTemplate.queryForObject(SQL_FIND_BY_PK, paramsMap, ticketMapper));
		}
		catch (EmptyResultDataAccessException e) {
			ticketOptional = Optional.empty();
		}

		return ticketOptional;
	}

	@Override
	public List<Ticket> findTicketsByPresentationId(Long presentationId) {
		return jdbcTemplate.query(SQL_FIND_BY_PRESENTATION_ID,
			Map.of("presentation_id", presentationId), ticketMapper);
	}

	@Override
	public List<Ticket> findTicketsByPersonId(Long personId) {
		return jdbcTemplate.query(SQL_FIND_BY_OWNER_ID,
			Map.of("owner_id", personId), ticketMapper);
	}

	@Override
	public void update(Ticket ticket) {
		Map<String, Object> paramsMap = Map.of(
			"presentation_id", ticket.getPresentationId(),
			"row", ticket.getRow(),
			"place", ticket.getPlace(),
			"owner_id", ticket.getOwnerId());

		jdbcTemplate.update(SQL_UPDATE_TICKET, paramsMap);
	}

	@Override
	public void delete(Long presentationId, Integer row, Integer place) {
		Map<String, Object> paramsMap = Map.of(
			"presentation_id", presentationId,
			"row", row,
			"place", place);

		jdbcTemplate.update(SQL_DELETE_TICKET, paramsMap);
	}

	@Override
	public List<Ticket> findTicketsByPresentationIdAndByRowOrderByPlace(
		Long presentationId, Integer row) {
		Map<String, Object> paramsMap = Map.of(
			"presentation_id", presentationId,
			"row", row);

		return jdbcTemplate.query(SQL_FIND_BY_PRESENTATION_AND_ROW,
			paramsMap, ticketMapper);
	}
}


