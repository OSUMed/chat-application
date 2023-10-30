package com.osumed.chatapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.osumed.chatapplication.domain.Person;
import com.osumed.chatapplication.repository.PersonRepository;

@Service
public class PersonService {

    // Declare a HashMap with Long keys and Person values
    private PersonRepository personRepository;
    private Integer personId;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = new PersonRepository();
    }

    public Person getPerson(Long userId) {
        // Retrieve a person from the map by user ID
        return personRepository.getPerson(userId);
    }

    public void addPerson(String name) {
        // Retrieve a person from the map by user ID
        Person person = new Person(name, personId);
        personId = personId + 1;
        personRepository.addPerson(person);
    }
}
