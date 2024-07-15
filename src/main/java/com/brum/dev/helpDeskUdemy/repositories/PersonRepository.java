package com.brum.dev.helpDeskUdemy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brum.dev.helpDeskUdemy.domain.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

	Optional<Person> findByEmail(String email);
	
	Optional<Person> findByCpf(String cpf);
	
}
