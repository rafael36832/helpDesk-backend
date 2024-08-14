package com.brum.dev.helpDeskUdemy.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brum.dev.helpDeskUdemy.domain.dtos.TicketDTO;
import com.brum.dev.helpDeskUdemy.domain.entities.Ticket;
import com.brum.dev.helpDeskUdemy.services.TicketService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "https://helpdesk-backend-prod-dc623e3fe50b.herokuapp.com")
@RequestMapping(value = "/ticket")
public class TicketController {

	@Autowired
	private TicketService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TicketDTO> findByid(@PathVariable Integer id) {
		Ticket response = this.service.findByid(id);
		TicketDTO dto = new TicketDTO(response);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<List<TicketDTO>> findAll() {
		List<Ticket> response = this.service.findAll();
		List<TicketDTO> responseDTO = response.stream().map(x -> new TicketDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping
	public ResponseEntity<TicketDTO> create(@Valid @RequestBody TicketDTO dto) {
		Ticket ticket = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TicketDTO> update(@PathVariable Integer id, @Valid @RequestBody TicketDTO dto) {
		Ticket ticket = service.update(id, dto);
		return ResponseEntity.ok().body(new TicketDTO(ticket));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TicketDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
