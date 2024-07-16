package com.brum.dev.helpDeskUdemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.dtos.TicketDTO;
import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.domain.entities.Ticket;
import com.brum.dev.helpDeskUdemy.domain.enums.Priority;
import com.brum.dev.helpDeskUdemy.domain.enums.Status;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.NotFoundException;
import com.brum.dev.helpDeskUdemy.repositories.TicketRepository;

import jakarta.validation.Valid;

@Service
public class TicketService {

	@Autowired
	TicketRepository repository;

	@Autowired
	TechnicianService technicianService;

	@Autowired
	ClientService clientService;

	public Ticket findByid(Integer id) {
		Optional<Ticket> response = repository.findById(id);
		return response.orElseThrow(() -> new NotFoundException("Object not found: Ticket id: " + id));
	}

	public List<Ticket> findAll() {
		return repository.findAll();
	}

	public Ticket create(TicketDTO dto) {
		return repository.save(newTicket(dto));
	}

	public Ticket update(Integer id, @Valid TicketDTO dto) {
		dto.setId(id);
		Ticket ticket = this.findByid(id);
		ticket = newTicket(dto);
		return repository.save(ticket);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	private Ticket newTicket(TicketDTO dto) {
		Technician technician = technicianService.findByid(dto.getTechnician());
		Client client = clientService.findByid(dto.getClient());

		Ticket ticket = new Ticket();
		if (dto.getId() != null) {
			ticket.setId(dto.getId());
		}

		ticket.setTechnician(technician);
		ticket.setClient(client);
		ticket.setPriority(Priority.toEnum(dto.getPriority()));
		ticket.setStatus(Status.toEnum(dto.getStatus()));
		ticket.setTitle(dto.getTitle());
		ticket.setObservations(dto.getObservations());

		return ticket;
	}

}
