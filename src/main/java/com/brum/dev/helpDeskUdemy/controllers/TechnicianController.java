package com.brum.dev.helpDeskUdemy.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.brum.dev.helpDeskUdemy.domain.dtos.TechnicianDTO;
import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.services.TechnicianService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
	@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
	public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO dto) {
		Technician technician = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(technician.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
	public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO dto) {
		Technician technician = service.update(id, dto);
		return ResponseEntity.ok().body(new TechnicianDTO(technician));
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
	public ResponseEntity<TechnicianDTO> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
