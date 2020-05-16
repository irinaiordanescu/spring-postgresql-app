package com.example.demo.dao;

import com.example.demo.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    //avem 2 metode

    //1: inseram o persoana cu un id dat
    int insertPerson(UUID id, Person person);

    //2: inseram o persoana cu un id random
    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    //se scriu doar metodele care vor fi implementate in 2 interfete
    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, String name);
}
