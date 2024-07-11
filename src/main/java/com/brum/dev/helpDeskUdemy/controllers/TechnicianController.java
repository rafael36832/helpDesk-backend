package com.brum.dev.helpDeskUdemy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.services.TechnicianService;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianController {

	@Autowired
	private TechnicianService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Technician> findByid(@PathVariable Integer id){
		Technician response = this.service.findByid(id);
		return ResponseEntity.ok().body(response);
	}
	
}
