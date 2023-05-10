package com.samuel.medico.model.usuario;

public class AutenticacaoDto {

	String login; 
	String senha;
	
	public AutenticacaoDto() {
	}
	
	public AutenticacaoDto(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
