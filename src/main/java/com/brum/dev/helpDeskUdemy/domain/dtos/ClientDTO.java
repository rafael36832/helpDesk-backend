package com.brum.dev.helpDeskUdemy.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;

	protected String name;

	protected String cpf;

	protected String email;

	protected String password;

	protected Set<Profile> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.creationDate = client.getCreationDate();
		this.email = client.getEmail();
		this.password = client.getPassword();
		this.profiles = client.getProfiles();
	}

}
