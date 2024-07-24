package com.brum.dev.helpDeskUdemy.domain.dtos;

public class LoginResponseDTO {
	
	private String accessToken;
	
	private Long expiresIn;
	
	public LoginResponseDTO(String accessToken, Long expiresIn) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
