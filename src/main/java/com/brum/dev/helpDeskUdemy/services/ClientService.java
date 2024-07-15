package com.brum.dev.helpDeskUdemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.dtos.ClientDTO;
import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.domain.entities.Person;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.DataIntegrityViolationException;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.NotFoundException;
import com.brum.dev.helpDeskUdemy.repositories.ClientRepository;
import com.brum.dev.helpDeskUdemy.repositories.PersonRepository;

import jakarta.validation.Valid;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private PersonRepository personRepository;

	public Client findByid(Integer id) {
		Optional<Client> response = repository.findById(id);
		return response.orElseThrow(() -> new NotFoundException("Object not found: Client id: " + id));
	}

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client create(ClientDTO dto) {
		dto.setId(null);
		this.verifyCpfAndEmail(dto);
		Client client = new Client(dto);
		return repository.save(client);
	}

	public Client update(Integer id, @Valid ClientDTO dto) {
		dto.setId(id);
		Client client = this.findByid(id);
		this.verifyCpfAndEmail(dto);
		client = new Client(dto);
		return repository.save(client);
	}

	public void delete(Integer id) {
		Client client = this.findByid(id);
		if (client.getTickets().size() > 0) {
			throw new DataIntegrityViolationException(
					"There are tickets associated with this client. You cannot delete it!");
		}

		repository.deleteById(id);
	}

	private void verifyCpfAndEmail(ClientDTO dto) {
		Optional<Person> personByCpf = this.personRepository.findByCpf(dto.getCpf());
		if (personByCpf.isPresent() && dto.getId() != personByCpf.get().getId()) {
			throw new DataIntegrityViolationException("CPF already registered");
		}

		Optional<Person> personByEmail = this.personRepository.findByEmail(dto.getEmail());
		if (personByEmail.isPresent() && dto.getId() != personByEmail.get().getId()) {
			throw new DataIntegrityViolationException("Email already registered");
		}
	}
}
