package com.brum.dev.helpDeskUdemy.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TechnicianDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	
	@NotNull(message = "The field Name cannot be null")
	protected String name;
	
	@NotNull(message = "The field CPF cannot be null")
	@Column(unique = true)
	protected String cpf;
	
	@NotNull(message = "The field Email cannot be null")
	@Column(unique = true)
	protected String email;

	@NotNull(message = "The field Password cannot be null")
	protected String password;

	protected Profile profile;

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();
	
	public TechnicianDTO() {
		super();
	}

	public TechnicianDTO(Technician technician) {
		super();
		this.id = technician.getId();
		this.name = technician.getName();
		this.cpf = technician.getCpf();
		this.email = technician.getEmail();
		this.password = technician.getPassword();
		this.creationDate = technician.getCreationDate();
		this.profile = technician.getProfile();
	}
}
