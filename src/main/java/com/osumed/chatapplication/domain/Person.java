package com.osumed.chatapplication.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
public class Person {
    private String name;
    private Long personId;

    public Person(String name, Long personId) {
        this.name = name;
        this.personId = personId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 

   
}