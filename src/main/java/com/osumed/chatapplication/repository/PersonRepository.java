package com.osumed.chatapplication.repository;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.osumed.chatapplication.domain.Person;

@Repository
public class PersonRepository {
    // Declare a HashMap with Long keys and Person values
    private HashMap<Long, Person> personMap;

    @Autowired
    public PersonRepository() {
        this.personMap = new HashMap<>();
    }

    public Person getPerson(Long userId) {
        // Retrieve a person from the map by user ID
        return personMap.get(userId);
    }

    public void addPerson(Person person) {
        personMap.put(person.getPersonId(), person);
    }
}
