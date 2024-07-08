package com.brum.dev.helpDeskUdemy.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.domain.entities.Ticket;
import com.brum.dev.helpDeskUdemy.domain.enums.Priority;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.brum.dev.helpDeskUdemy.domain.enums.Status;
import com.brum.dev.helpDeskUdemy.repositories.ClientRepository;
import com.brum.dev.helpDeskUdemy.repositories.TechnicianRepository;
import com.brum.dev.helpDeskUdemy.repositories.TicketRepository;

@Service
public class TestDBService {
	
	@Autowired
	private TechnicianRepository technicianRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private TicketRepository ticketRepository; 
	
	public void instanceDB() {
		
		Technician tech1 = new Technician(null, "Jose", "97637388069", "jose@email.com", "****");
		tech1.addProfile(Profile.ADMIN);
		
		Client cli1 = new Client(null, "Joao", "93207538061", "joao@email.com", "****");
		
		Ticket tic1 = new Ticket(null, Priority.MEDIUM, Status.IN_PROGRESS, "Ticket N1", "First Ticket", tech1, cli1); 
		
		technicianRepository.saveAll(Arrays.asList(tech1));
		clientRepository.saveAll(Arrays.asList(cli1));
		ticketRepository.saveAll(Arrays.asList(tic1));
		
	}

}
