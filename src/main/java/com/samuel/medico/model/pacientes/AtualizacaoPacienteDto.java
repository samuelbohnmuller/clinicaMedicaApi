package com.samuel.medico.model.pacientes;

import com.samuel.medico.model.EnderecoDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoPacienteDto {

	@NotNull
    Long id;
    String nome;
    String telefone;
    @Valid
    EnderecoDto endereco;

	public AtualizacaoPacienteDto() {
	}

	public AtualizacaoPacienteDto(Long id, String nome, String telefone, EnderecoDto endereco) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public EnderecoDto getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}
    
    
	
}
