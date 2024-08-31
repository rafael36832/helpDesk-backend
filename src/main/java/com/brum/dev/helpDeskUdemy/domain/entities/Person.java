package com.brum.dev.helpDeskUdemy.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.brum.dev.helpDeskUdemy.domain.dtos.LoginRequestDTO;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public abstract class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@NotNull(message = "The field Name cannot be null")
	protected String name;

	@CPF
	@NotNull(message = "The field CPF cannot be null")
	@Column(unique = true)
	protected String cpf;

	@NotNull(message = "The field Email cannot be null")
	@Column(unique = true)
	protected String email;

	@NotNull(message = "The field Password cannot be null")
	protected String password;

	@NotNull(message = "The field Profile cannot be null")
	protected Profile profile;

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public Person() {
		super();
	}

	public Person(Integer id, String name, String cpf, String email, Profile profile, String password) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

	public boolean isLoginCorrect(LoginRequestDTO loginRequestDTO, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(loginRequestDTO.getPassword(), this.password);
	}

}
