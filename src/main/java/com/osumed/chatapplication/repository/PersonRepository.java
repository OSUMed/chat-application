package com.osumed.chatapplication.repository;

import org.springframework.stereotype.Repository;
import com.osumed.chatapplication.domain.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    private final List<Person> persons;

    public PersonRepository() {
        this.persons = new ArrayList<>();
    }

    public Optional<Person> getPerson(Integer userId) {
        // Use a stream to search for the person by userId
        return persons.stream()
                .filter(person -> person.getPersonId().equals(userId))
                .findFirst();
    }

    public Person addPerson(Person person) {
        persons.add(person);
        return person;
    }
}
