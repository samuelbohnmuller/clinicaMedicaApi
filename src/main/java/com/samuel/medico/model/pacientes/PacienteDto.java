package com.samuel.medico.model.pacientes;

import com.samuel.medico.model.EnderecoDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PacienteDto {

	@NotBlank
	String nome;
	@NotBlank @Email
    String email;
	@NotBlank
    String telefone;
	@NotBlank @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    String cpf;
	@NotNull @Valid
    EnderecoDto enderecoDto;

	public PacienteDto() {
	}

	public PacienteDto(String nome, String email, String telefone, String cpf, EnderecoDto enderecoDto) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.enderecoDto = enderecoDto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public EnderecoDto getEnderecoDto() {
		return enderecoDto;
	}
	public void setEnderecoDto(EnderecoDto enderecoDto) {
		this.enderecoDto = enderecoDto;
	}
    
    
}
