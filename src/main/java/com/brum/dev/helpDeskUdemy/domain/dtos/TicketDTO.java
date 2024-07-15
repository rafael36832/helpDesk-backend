package com.brum.dev.helpDeskUdemy.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.brum.dev.helpDeskUdemy.domain.entities.Client;
import com.brum.dev.helpDeskUdemy.domain.entities.Technician;
import com.brum.dev.helpDeskUdemy.domain.entities.Ticket;
import com.brum.dev.helpDeskUdemy.domain.enums.Priority;
import com.brum.dev.helpDeskUdemy.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketDTO implements Serializable {

	private static final long serialVersionUID = 1L;

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

	private String observations;

	private Technician technician;

	@NotNull
	private Client client;

	public TicketDTO() {
		super();
	}

	public TicketDTO(Integer id, LocalDate openedDate, LocalDate closedDate, @NotNull Priority priority,
			@NotNull Status status, @NotNull String title, String observations, Technician technician,
			@NotNull Client client) {
		super();
		this.id = id;
		this.openedDate = openedDate;
		this.closedDate = closedDate;
		this.priority = priority;
		this.status = status;
		this.title = title;
		this.observations = observations;
		this.technician = technician;
		this.client = client;
	}

	public TicketDTO(Ticket ticket) {
		super();
		this.id = ticket.getId();
		this.openedDate = ticket.getOpenedDate();
		this.closedDate = ticket.getClosedDate();
		this.priority = ticket.getPriority();
		this.status = ticket.getStatus();
		this.title = ticket.getTitle();
		this.observations = ticket.getObservations();
		this.technician = ticket.getTechnician();
		this.client = ticket.getClient();
	}

}
