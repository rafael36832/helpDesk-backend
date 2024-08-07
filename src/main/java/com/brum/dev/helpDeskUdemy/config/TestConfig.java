package com.brum.dev.helpDeskUdemy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.brum.dev.helpDeskUdemy.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService testDBService;

    @Bean
    boolean instanceDB() {
		this.testDBService.instanceDB();
		return true;
	}

}
