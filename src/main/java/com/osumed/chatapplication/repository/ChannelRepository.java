package com.osumed.chatapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osumed.chatapplication.domain.Person;

@Repository
public interface ChannelRepository extends JpaRepository<Person, Long>{

}
