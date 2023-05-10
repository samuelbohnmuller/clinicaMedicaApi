package com.samuel.medico.model.medico;

import com.samuel.medico.model.EnderecoDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MedicoDto {

	@NotBlank
	String nome; 
	@NotBlank
	String telefone; 
	@NotBlank @Email
	String email; 
	@NotBlank @Pattern(regexp = "\\d{4,6}") //4 a 6 digitos.
	String crm;
	@NotNull //Precisa ser essa anotação pois não é string.
	Especialidade especialidade; 
	@NotNull @Valid //Valid é para validar outro dto que também terá atributos com validações.
	EnderecoDto endereco;
	
	public MedicoDto(String nome, String email, String crm, Especialidade especialidade, EnderecoDto endereco) {
		this.nome = nome;
		this.email = email;
		this.crm = crm;
		this.especialidade = especialidade;
		this.endereco = endereco;
	}

	public MedicoDto() {
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

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
}
