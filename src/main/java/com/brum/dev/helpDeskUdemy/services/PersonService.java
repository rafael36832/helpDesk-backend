package com.brum.dev.helpDeskUdemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.entities.Person;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.NotFoundException;
import com.brum.dev.helpDeskUdemy.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public Person findByid(Integer id) {
		Optional<Person> response = repository.findById(id);
		return response.orElseThrow(() -> new NotFoundException("Object not found: Client id: " + id));
	}

	public List<Person> findAll() {
		return repository.findAll();
	}

	public Person findByEmail(String email) {
		Optional<Person> response = repository.findByEmail(email);
		return response.orElseThrow(() -> new NotFoundException("Object not found: Person email: " + email));
	}

}
