package com.brum.dev.helpDeskUdemy.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.brum.dev.helpDeskUdemy.domain.dtos.TicketDTO;
import com.brum.dev.helpDeskUdemy.domain.enums.Priority;
import com.brum.dev.helpDeskUdemy.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate openedDate = LocalDate.now();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate closedDate; 
	
	@NotNull
	private Priority priority;
	
	@NotNull
	private Status status;
	
	@NotNull
	private String title;
	
	@NotNull
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "technician_id")
	private Technician technician;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	public Ticket() {
		super();
	}	

	public Ticket(Integer id, @NotNull Priority priority,
			@NotNull Status status, @NotNull String title, String description, Technician technician, Client client) {
		super();
		this.id = id;
		this.priority = priority;
		this.status = status;
		this.title = title;
		this.description = description;
		this.technician = technician;
		this.client = client;
	}

	
	public Ticket(TicketDTO ticket) {
		super();
		this.id = ticket.getId();
		this.priority = Priority.toEnum(ticket.getPriority());
		this.status = Status.toEnum(ticket.getStatus());
		this.title = ticket.getTitle();
		this.description = ticket.getDescription();
		this.technician.id = ticket.getTechnician();
		this.client.id = ticket.getClient();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public void setStatus(Status status) {
		if(Status.CLOSED.equals(status)) {
			this.setClosedDate(LocalDate.now());
		}
		this.status = status;
	}
	
}
