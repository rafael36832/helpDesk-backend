package com.brum.dev.helpDeskUdemy.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.brum.dev.helpDeskUdemy.domain.entities.Ticket;
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
	private Integer priority;

	@NotNull
	private Integer status;

	@NotNull
	private String title;

	@NotNull
	private String description;

	private Integer technician;

	@NotNull
	private Integer client;
	
	private String technicianName;
	
	private String clientName;

	public TicketDTO() {
		super();
	}
	
	public TicketDTO(Integer id, LocalDate openedDate, LocalDate closedDate, @NotNull Integer priority,
			@NotNull Integer status, @NotNull String title, @NotNull String description, Integer technician,
			@NotNull Integer client, String technicianName, String clientName) {
		super();
		this.id = id;
		this.openedDate = openedDate;
		this.closedDate = closedDate;
		this.priority = priority;
		this.status = status;
		this.title = title;
		this.description = description;
		this.technician = technician;
		this.client = client;
		this.technicianName = technicianName;
		this.clientName = clientName;
	}
	
	public TicketDTO(Ticket ticket) {
		super();
		this.id = ticket.getId();
		this.openedDate = ticket.getOpenedDate();
		this.closedDate = ticket.getClosedDate();
		this.priority = ticket.getPriority().getCode();
		this.status = ticket.getStatus().getCode();
		this.title = ticket.getTitle();
		this.description = ticket.getDescription();
		this.technician = ticket.getTechnician().getId();
		this.client = ticket.getClient().getId();
		this.technicianName = ticket.getTechnician().getName();
		this.clientName = ticket.getClient().getName();
	}

}
