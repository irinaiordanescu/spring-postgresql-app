package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {
    private final UUID id;
    @NotBlank //trebuie sa am mereu un nume <=> cu not null
    private final String name;

    //constructor
    //@JsonProperty("id"), @JsonProperty("name") - decoratiuni json; cand afiseaza sa scrie id si name
    //ex:
    //    {
    //        "id": "53a28211-e082-47c3-bfaf-ef79c15e0ca7",
    //        "name": "FROM POSTGRES DB"
    //    }
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
