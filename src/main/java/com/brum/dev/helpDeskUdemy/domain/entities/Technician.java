package com.brum.dev.helpDeskUdemy.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.brum.dev.helpDeskUdemy.domain.dtos.TechnicianDTO;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Technician extends Person{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "technician")
	private List<Ticket> tickets = new ArrayList<>();
	
	public Technician() {
		super();
		addProfile(Profile.TECHNICIAN);
	}

	public Technician(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfile(Profile.TECHNICIAN);
	}
	
	public Technician(TechnicianDTO dto){
		super();
		this.id = dto.getId();
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.creationDate = dto.getCreationDate();
		this.profiles = dto.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());		
	}

	public void addTicket(Ticket ticket){
		this.tickets.add(ticket);
	}

}
