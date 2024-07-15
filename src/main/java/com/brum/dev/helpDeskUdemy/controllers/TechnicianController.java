package com.brum.dev.helpDeskUdemy.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brum.dev.helpDeskUdemy.domain.dtos.TechnicianDTO;
import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.services.TechnicianService;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianController {

	@Autowired
	private TechnicianService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> findByid(@PathVariable Integer id) {
		Technician response = this.service.findByid(id);
		TechnicianDTO dto = new TechnicianDTO(response);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping
	public ResponseEntity<List<TechnicianDTO>> findAll() {
		List<Technician> response = this.service.findAll();
		List<TechnicianDTO> responseDTO = response.stream().map(x -> new TechnicianDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping
	public ResponseEntity<TechnicianDTO> create(@RequestBody TechnicianDTO dto) {
		Technician technician = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(technician.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
