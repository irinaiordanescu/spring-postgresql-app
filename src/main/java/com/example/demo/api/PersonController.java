package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/person") //adresa de accesare (frontend)
@RestController //metodele pe care le acceseaza clientii
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{idPerson}")
    public Person getPersonByID(@PathVariable("idPerson") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{idPerson}")
    public void deletePersonById(@PathVariable("idPerson") UUID id) {
        personService.deletePersonById(id);
    }

    @PutMapping(path = "{idPerson}")
    public void updatePersonById(@PathVariable("idPerson") UUID id, @Valid @NotNull @RequestBody String nameToUpdate) {
        personService.updatePersonById(id, nameToUpdate);
    }
}
//POST add a result from server
//PUT modifying a result from a server
//GET retrieving data from server
//DELETE deleting a result from server