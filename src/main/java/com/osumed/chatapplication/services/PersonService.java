package com.osumed.chatapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.osumed.chatapplication.domain.Person;
import com.osumed.chatapplication.repository.PersonRepository;

import java.util.HashMap;

@Service
public class PersonService {

    // Declare a HashMap with Long keys and Person values
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = new PersonRepository();
    }

    public Person getPerson(Long userId) {
        // Retrieve a person from the map by user ID
        return personRepository.getPerson(userId);
    }

    public void addPerson(String name, Long userId) {
        // Retrieve a person from the map by user ID
        Person person = new Person(name, userId);
        personRepository.addPerson(person);
    }
}
