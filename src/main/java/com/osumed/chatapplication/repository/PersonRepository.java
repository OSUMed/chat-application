package com.osumed.chatapplication.repository;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.osumed.chatapplication.domain.Person;

@Repository
public class PersonRepository {
    // Declare a HashMap with Long keys and Person values
    private HashMap<Integer, Person> personMap;

    @Autowired
    public PersonRepository() {
        this.personMap = new HashMap<>();
    }

    public Person getPerson(Integer userId) {
        // Retrieve a person from the map by user ID
        return personMap.get(userId);
    }

    public Person addPerson(Person person) {
        personMap.put(person.getPersonId(), person);
        Person inputtedPerson = getPerson(person.getPersonId());
        return inputtedPerson;
    }
}
