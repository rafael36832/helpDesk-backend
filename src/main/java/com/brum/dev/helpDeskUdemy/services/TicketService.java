package com.brum.dev.helpDeskUdemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.entities.Ticket;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.NotFoundException;
import com.brum.dev.helpDeskUdemy.repositories.TicketRepository;

@Service
public class TicketService {

	@Autowired
	TicketRepository repository;
	
	public Ticket findByid(Integer id) {
		Optional<Ticket> response = repository.findById(id);
		return response.orElseThrow(() -> new NotFoundException("[TicketService] Object not found: Ticket id: " + id));
	}

	public List<Ticket> findAll() {
		return repository.findAll();
	}
}
