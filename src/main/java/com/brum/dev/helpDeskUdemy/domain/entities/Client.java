package com.brum.dev.helpDeskUdemy.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.brum.dev.helpDeskUdemy.domain.dtos.ClientDTO;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Client extends Person {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Ticket> tickets = new ArrayList<>();

	public Client() {
		super();
		addProfile(Profile.CLIENT);
	}

	public Client(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfile(Profile.CLIENT);
	}

	public Client(ClientDTO dto) {
		super();
		this.id = dto.getId();
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.creationDate = dto.getCreationDate();
		this.profiles = dto.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
	}

	public void addTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}

}
