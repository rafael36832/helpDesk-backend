package com.brum.dev.helpDeskUdemy.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
import lombok.Data;

@Data
@Entity
public abstract class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	protected String name;
	
	@Column(unique = true)
	protected String cpf;
	
	@Column(unique = true)
	protected String email;

	protected String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PROFILES")
	protected Set<Integer> profiles = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public Person() {
		super();
		addProfile(Profile.CLIENT);
	}

	public Person(Integer id, String name, String cpf, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		addProfile(Profile.CLIENT);
	}
	
	public Set<Profile> getProfiles() {
		return profiles.stream().map(p -> Profile.toEnum(p)).collect(Collectors.toSet());
	}

	public void addProfile(Profile profile) {
		this.profiles.add(profile.getCode());
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
		
}
