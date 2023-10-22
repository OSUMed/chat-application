package com.osumed.chatapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osumed.chatapplication.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
