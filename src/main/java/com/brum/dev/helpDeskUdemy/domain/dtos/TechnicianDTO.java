package com.brum.dev.helpDeskUdemy.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TechnicianDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;

	protected String name;

	protected String cpf;

	protected String email;

	protected String password;

	protected Set<Profile> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();
	
	public TechnicianDTO() {
		super();
		profiles.add(Profile.TECHNICIAN);
	}

	public TechnicianDTO(Technician technician) {
		super();
		this.id = technician.getId();
		this.name = technician.getName();
		this.cpf = technician.getCpf();
		this.email = technician.getEmail();
		this.password = technician.getPassword();
		this.creationDate = technician.getCreationDate();
		this.profiles = technician.getProfiles();
		profiles.add(Profile.TECHNICIAN);
	}
}
