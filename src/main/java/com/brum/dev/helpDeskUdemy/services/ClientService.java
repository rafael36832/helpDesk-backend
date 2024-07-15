package com.brum.dev.helpDeskUdemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.NotFoundException;
import com.brum.dev.helpDeskUdemy.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client findByid(Integer id) {
		Optional<Client> response = repository.findById(id);
		return response.orElseThrow(() -> new NotFoundException("Object not found: Client id: " + id));
	}

	public List<Client> findAll() {
		return repository.findAll();
	}
}
