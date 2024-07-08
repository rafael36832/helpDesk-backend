package com.brum.dev.helpDeskUdemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brum.dev.helpDeskUdemy.domain.entities.Technician;

public interface TechnicianRepository  extends JpaRepository<Technician, Integer>{

}
