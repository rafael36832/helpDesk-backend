package com.brum.dev.helpDeskUdemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.exceptions.NotFoundException;
import com.brum.dev.helpDeskUdemy.repositories.TechnicianRepository;

@Service
public class TechnicianService {
	
	@Autowired
	private TechnicianRepository repository;
	
	public Technician findByid(Integer id) {
		Optional<Technician> response = repository.findById(id);
		return response.orElseThrow(() -> new NotFoundException("Object not found: Technician id: " + id)); 
	}

	public List<Technician> findAll() {
		return repository.findAll();
	}
	
	
	
	

}
