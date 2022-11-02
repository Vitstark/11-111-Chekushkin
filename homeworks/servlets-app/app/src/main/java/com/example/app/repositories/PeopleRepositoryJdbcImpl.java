package com.example.app.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import util.mappers.PeopleMapper;
import com.example.app.models.Person;

import javax.sql.DataSource;
import java.util.*;

public class PeopleRepositoryJdbcImpl implements PeopleRepository {
    //language=SQL
    private static final String SQL_SELECT_ALL_PEOPLE =
            "select * from person";

    //language=SQL
    private static final String SQL_DELETE_PERSON_BY_ID =
            "delete from person where id = :id";
    //language=SQL
    private static final String SQL_SELECT_BY_ID =
            "select * from person where id = :id";
    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL =
            "select * from person where email = :email";
    //language=SQL
    private static final String SQL_UPDATE_PERSON =
            "update person set first_name = :first_name, last_name = :last_name, " +
                    "email = :email, pass = :pass " +
                    "where id = :id";

    private static final RowMapper<Person> personMapper = new PeopleMapper();

    private NamedParameterJdbcTemplate jdbcTemplate;

    public PeopleRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void save(Person person) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("email", person.getEmail());
        paramsAsMap.put("first_name", person.getFirstName());
        paramsAsMap.put("last_name", person.getLastName());
        paramsAsMap.put("pass", person.getPassword());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate());

        Long id = insert.withTableName("person")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap)).longValue();

        person.setId(id);
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PEOPLE, personMapper);
    }

    @Override
    public Optional<Person> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id), personMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Person person) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("id", person.getId());
        paramsAsMap.put("email", person.getEmail());
        paramsAsMap.put("first_name", person.getFirstName());
        paramsAsMap.put("last_name", person.getLastName());
        paramsAsMap.put("pass", person.getPassword());

        jdbcTemplate.update(SQL_UPDATE_PERSON, paramsAsMap);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_PERSON_BY_ID, Collections.singletonMap("id", id));
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL,
                    Collections.singletonMap("email", email), personMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
