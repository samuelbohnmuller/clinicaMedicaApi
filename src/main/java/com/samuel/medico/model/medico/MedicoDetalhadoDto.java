package com.samuel.medico.model.medico;

import com.samuel.medico.model.Endereco;

public class MedicoDetalhadoDto {

	Long id; 
	String nome; 
	String email; 
	String crm; 
	String telefone; 
	Especialidade especialidade;
	Endereco endereco;
	
	public MedicoDetalhadoDto(Medico medico) {
		this.id = medico.getId();
		this.nome = medico.getNome();
		this.email = medico.getEmail();
		this.crm = medico.getCrm();
		this.telefone = medico.getTelefone();
		this.especialidade = medico.getEspecialidade();
		this.endereco = medico.getEndereco();
	}

	public MedicoDetalhadoDto() {
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
