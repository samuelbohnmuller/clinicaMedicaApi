package com.samuel.medico.model.medico;

public class listaMedicosDto {

	Long id;
	String nome; 
	String email; 
	String crm; 
	Especialidade especialidade;
	
	public listaMedicosDto() {
	}

	public listaMedicosDto(Medico medico) {
		this.nome = medico.getNome();
		this.email = medico.getEmail();
		this.crm = medico.getCrm();
		this.especialidade = medico.getEspecialidade();
		this.id = medico.getId();
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
	public Especialidade getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
