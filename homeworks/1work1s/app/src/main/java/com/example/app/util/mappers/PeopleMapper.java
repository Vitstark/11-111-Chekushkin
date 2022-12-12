package com.example.app.util.mappers;

import com.example.app.models.Person;
import com.example.app.models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Person.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .name(rs.getString("name"))
                .password(rs.getString("pass"))
                .role(Role.valueOf(rs.getString("role")))
                .build();
    }
}
