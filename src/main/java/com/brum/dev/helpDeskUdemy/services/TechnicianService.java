package com.brum.dev.helpDeskUdemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.dtos.TechnicianDTO;
import com.brum.dev.helpDeskUdemy.domain.entities.Person;
import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.DataIntegrityViolationException;
import com.brum.dev.helpDeskUdemy.exceptions.Entities.NotFoundException;
import com.brum.dev.helpDeskUdemy.repositories.PersonRepository;
import com.brum.dev.helpDeskUdemy.repositories.TechnicianRepository;

@Service
public class TechnicianService {
	
	@Autowired
	private TechnicianRepository repository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public Technician findByid(Integer id) {
		Optional<Technician> response = repository.findById(id);
		return response.orElseThrow(() -> new NotFoundException("Object not found: Technician id: " + id)); 
	}

	public List<Technician> findAll() {
		return repository.findAll();
	}
	
	public Technician create(TechnicianDTO dto) {
		dto.setId(null);
		this.verifyCpfAndEmail(dto);
		Technician technician = new Technician(dto);
		return repository.save(technician);
	}

	private void verifyCpfAndEmail(TechnicianDTO dto) {
		Optional<Person> personByCpf = this.personRepository.findByCpf(dto.getCpf());
		if(personByCpf.isPresent() && dto.getId() != personByCpf.get().getId()) {
			throw new DataIntegrityViolationException("CPF already registered");
		}
		
		Optional<Person> personByEmail = this.personRepository.findByEmail(dto.getEmail());
		if(personByEmail.isPresent() && dto.getId() != personByEmail.get().getId()) {
			throw new DataIntegrityViolationException("Email already registered");
		}
	}
	
	
	
	

}
