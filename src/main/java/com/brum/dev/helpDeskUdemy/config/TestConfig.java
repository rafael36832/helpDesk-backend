package com.brum.dev.helpDeskUdemy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.brum.dev.helpDeskUdemy.services.TestDBService;

@Configurable
@Profile("test")
public class TestConfig {
	
	@Autowired
	private TestDBService testDBService;
	
	@Bean
	public void  instanceDB() {
		this.testDBService.instanceDB();
	}

}
