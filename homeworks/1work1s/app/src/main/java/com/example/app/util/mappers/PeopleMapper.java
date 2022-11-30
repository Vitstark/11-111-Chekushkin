package com.example.app.util.mappers;

import com.example.app.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Person.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .password(rs.getString("pass"))
                .build();
    }
}
