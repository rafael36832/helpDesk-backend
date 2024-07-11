package com.brum.dev.helpDeskUdemy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.repositories.ClientRepository;


public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client findByid(Integer id) {
		Optional<Client> response = this.repository.findById(id);
		return response.orElse(null);
	}

}
