package com.brum.dev.helpDeskUdemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brum.dev.helpDeskUdemy.domain.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
