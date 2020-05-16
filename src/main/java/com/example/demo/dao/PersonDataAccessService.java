package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//e facuta pt baza de date(frontend: postman, baza de date: postgres)
@Repository("person") //ma lasa sa am multiple implementari
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person (idPerson, name) VALUES(?, ?)";
        return jdbcTemplate.update(sql,id, person.getName());
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT idPerson, name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID idPerson = UUID.fromString(resultSet.getString("idPerson")); //columnLabel: "id"
            String name = resultSet.getString("name"); //columnLabel: "name"
            return new Person(idPerson, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT idPerson, name FROM person WHERE idPerson = ?";
        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID idPerson = UUID.fromString(resultSet.getString("idPerson")); //columnLabel: "id"
                    String name = resultSet.getString("name"); //columnLabel: "name"
                    return new Person(idPerson, name);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE idPerson = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql,args);
    }

    @Override
    public int updatePersonById(UUID id, String name) {
        final String sql = "UPDATE person SET name = ? WHERE idPerson = ?";
        return jdbcTemplate.update(sql, name, id);
    }
}
