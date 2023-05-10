package com.samuel.medico.model.pacientes;

import com.samuel.medico.model.Endereco;

public class PacienteDetalhadoDto {

	Long id; 
	String nome; 
	String email; 
	String cpf; 
	String telefone; 
	Endereco endereco;
	
	public PacienteDetalhadoDto(Paciente paciente) {
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.email = paciente.getEmail();
		this.cpf = paciente.getCpf();
		this.telefone = paciente.getTelefone();
		this.endereco = paciente.getEndereco();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
