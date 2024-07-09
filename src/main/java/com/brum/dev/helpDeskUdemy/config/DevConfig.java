package com.brum.dev.helpDeskUdemy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.brum.dev.helpDeskUdemy.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;

    @Bean
    boolean instanceDB() {
		if(this.value.equals("create")) {
			dbService.instanceDB();
			return true;
		}
		return false;
	}

}
