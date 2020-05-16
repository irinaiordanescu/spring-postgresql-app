package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//e facuta pt ca nu am baza de date (frontend: postman, baza de date: e fake)
//ii spunem lui spring ca acesta cls trebuie sa fie instantiata ca o beens??? ca sa putem injecta in alte clase
@Repository("fakeDao") //ma lasa sa am multiple implementari
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>(); //am o lista numita BD care este de tip Person

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream() //inlocuieste for-urile si pot avea acces la mai multe fct dupa
                .filter(person -> person.getId().equals(id)) //filtreaza vectorul si returneaza valorile care respecta conditia de dupa -> (gen un if)
                .findFirst(); //in loc sa returneze un vector, returneaza doar prima valoarea din vector
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMayBe = selectPersonById(id); //a pus Optional pt ca nu este sigur ca exista oboectul cu id-ul respectiv
        //daca obiectul nu exista returneaza 0, altefel il sterge
        if(personMayBe.isEmpty()) {
            return 0;
        }
        DB.remove(personMayBe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, String name) {
        return selectPersonById(id)
                .map(person -> { //person - nume variabila; dupa -> e ce vreau sa returnez
                    int indexOfPersonToUpdate = DB.indexOf(person); //indexOf() - returneaza pozitia persoanei
                    if(indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(id, name)); //set() - seteaza valorile noi
                        return 1; //true
                    }
                    return 0; //false
                }) //modifica obiectele
                .orElse(0); // <=> else { return 0; }
    }

}
