package com.brum.dev.helpDeskUdemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brum.dev.helpDeskUdemy.domain.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
