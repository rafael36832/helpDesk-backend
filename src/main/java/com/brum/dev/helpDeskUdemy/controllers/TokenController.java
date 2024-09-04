package com.brum.dev.helpDeskUdemy.controllers;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brum.dev.helpDeskUdemy.domain.dtos.LoginRequestDTO;
import com.brum.dev.helpDeskUdemy.domain.dtos.LoginResponseDTO;
import com.brum.dev.helpDeskUdemy.domain.entities.Person;
import com.brum.dev.helpDeskUdemy.domain.enums.Profile;
import com.brum.dev.helpDeskUdemy.services.PersonService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TokenController {

	private final JwtEncoder jwtEncoder;

	@Autowired
	private final PersonService personService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public TokenController(JwtEncoder jwtEncoder, PersonService personService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.jwtEncoder = jwtEncoder;
		this.personService = personService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
		try {
			Person user = personService.findByEmail(dto.getUsername());
			if (!user.isLoginCorrect(dto, bCryptPasswordEncoder)) {
				throw new BadCredentialsException("User or password is invalid!");
			}
			
			var expiresIn = 172800l;
			var now = Instant.now();
			var scopes = user.getProfile().getDescription();

			var claims = JwtClaimsSet.builder().issuer("helpDeskBackend").subject(user.getId().toString()).issuedAt(now)
					.expiresAt(now.plusSeconds(expiresIn)).claim("scope", scopes).build();

			var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

			return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));

		} catch (Exception e) {
			throw new BadCredentialsException("User or password is invalid!");
		}
	}

}
