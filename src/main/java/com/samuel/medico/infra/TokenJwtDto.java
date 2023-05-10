package com.samuel.medico.infra;

public class TokenJwtDto {
	
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenJwtDto(String token) {
		this.token = token;
	}

	public TokenJwtDto() {
	}
	
}
