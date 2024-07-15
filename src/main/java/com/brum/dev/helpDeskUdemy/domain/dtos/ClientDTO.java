package com.brum.dev.helpDeskUdemy.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDTO implements Serializable {

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

	protected Set<Profile> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public ClientDTO() {
		super();
		this.profiles.add(Profile.CLIENT);
	}

	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.creationDate = client.getCreationDate();
		this.email = client.getEmail();
		this.password = client.getPassword();
		this.profiles = client.getProfiles();
		this.profiles.add(Profile.CLIENT);
	}

}
