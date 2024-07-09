package com.epam.valevataya.dao;

import com.epam.valevataya.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class PersonDao {
  private final JdbcTemplate jdbcTemplate;

  public PersonDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Person> getPeople() {
    return jdbcTemplate.query("select * from person", new PersonMapper());
  }

  public void save(Person person) {
    jdbcTemplate.update("INSERT INTO person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(),
            person.getEmail());
  }

  public Person getPerson(int id) {
    return (Person) jdbcTemplate.query("select * from person where id=?", new Object[]{id}, new PersonMapper())
            .stream()
            .findAny().orElse(null);
  }

  public void update(int id, Person updatedPerson) {
    jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
            updatedPerson.getAge(), updatedPerson.getEmail(), id);
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
  }
}
