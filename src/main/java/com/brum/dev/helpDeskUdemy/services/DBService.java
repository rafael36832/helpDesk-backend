package com.brum.dev.helpDeskUdemy.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.domain.entities.Ticket;
import com.brum.dev.helpDeskUdemy.domain.enums.Priority;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.brum.dev.helpDeskUdemy.domain.enums.Status;
import com.brum.dev.helpDeskUdemy.repositories.PersonRepository;
import com.brum.dev.helpDeskUdemy.repositories.TicketRepository;

@Service
public class DBService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void instanceDB() {

		Technician tec1 = new Technician(null, "Valdir Cezar", "55048215095", "valdir@mail.com", Profile.ADMIN, passwordEncoder.encode("123"));
		Technician tec2 = new Technician(null, "Richard Stallman", "90334707056", "stallman@mail.com", Profile.DEFAULT,passwordEncoder.encode("123"));
		Technician tec3 = new Technician(null, "Claude Elwood Shannon", "27106847054", "shannon@mail.com", Profile.DEFAULT, passwordEncoder.encode("123"));
		Technician tec4 = new Technician(null, "Tim Berners-Lee", "16272012039", "lee@mail.com", Profile.DEFAULT, passwordEncoder.encode("123"));
		Technician tec5 = new Technician(null, "Linus Torvalds", "77855617027", "linus@mail.com", Profile.DEFAULT, passwordEncoder.encode("123"));

		Client cli1 = new Client(null, "Albert Einstein", "11166189074", "einstein@mail.com", Profile.DEFAULT, passwordEncoder.encode("123"));
		Client cli2 = new Client(null, "Marie Curie", "32242914006", "curie@mail.com", Profile.DEFAULT, passwordEncoder.encode("123"));
		Client cli3 = new Client(null, "Charles Darwin", "79204383062", "darwin@mail.com", Profile.DEFAULT, passwordEncoder.encode("123"));
		Client cli4 = new Client(null, "Stephen Hawking", "17740968030", "hawking@mail.com", Profile.DEFAULT, passwordEncoder.encode("123"));
		Client cli5 = new Client(null, "Max Planck", "08139930083", "planck@mail.com", Profile.DEFAULT, passwordEncoder.encode("123"));

		Ticket c1 = new Ticket(null, Priority.MEDIUM, Status.IN_PROGRESS, "Ticket 1", "Teste Ticket 1", tec1, cli1);
		Ticket c2 = new Ticket(null, Priority.HIGH, Status.OPEN, "Ticket 2", "Teste Ticket 2", tec1, cli2);
		Ticket c3 = new Ticket(null, Priority.LOW, Status.CLOSED, "Ticket 3", "Teste Ticket 3", tec2, cli3);
		Ticket c4 = new Ticket(null, Priority.HIGH, Status.OPEN, "Ticket 4", "Teste Ticket 4", tec3, cli3);
		Ticket c5 = new Ticket(null, Priority.MEDIUM, Status.IN_PROGRESS, "Ticket 5", "Teste Ticket 5", tec2, cli1);
		Ticket c6 = new Ticket(null, Priority.LOW, Status.CLOSED, "Ticket 7", "Teste Ticket 6", tec1, cli5);

		personRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
		ticketRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

	}

}
