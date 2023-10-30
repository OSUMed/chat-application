package com.osumed.chatapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.osumed.chatapplication.domain.Person;
import com.osumed.chatapplication.repository.PersonRepository;

@Service
public class PersonService {

    // Declare a HashMap with Long keys and Person values
    private PersonRepository personRepository;
    private Integer personId = 0;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPerson(Integer userId) {
        return personRepository.getPerson(userId)
                .orElseThrow(() -> new RuntimeException("Person not found with userId: " + userId));
    }

    public Person addPerson(String name) {
        // Retrieve a person from the map by user ID
        Person person = new Person(name, personId);
        personId = personId + 1;
        Person returnedPerson = personRepository.addPerson(person);
        return returnedPerson;
    }
}
