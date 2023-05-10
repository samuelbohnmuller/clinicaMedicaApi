package com.samuel.medico.model.pacientes;

import com.samuel.medico.model.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;
    
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


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Boolean getAtivo() {
		return ativo;
	}


	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Paciente(PacienteDto pacientes) {
        this.ativo = true;
        this.nome = pacientes.getNome();
        this.email = pacientes.getEmail();
        this.telefone = pacientes.getTelefone();
        this.cpf = pacientes.getCpf();
        this.endereco = new Endereco(pacientes.getEnderecoDto());
    }
	
    public Paciente() {
	}

	public void excluir() {
        this.ativo = false;
    }
    
    public void atualizarInformacoes(AtualizacaoPacienteDto pacienteDto) {
        if (pacienteDto.getNome() != null) {
            this.nome = pacienteDto.getNome();
        }
        if (pacienteDto.getTelefone() != null) {
            this.telefone = pacienteDto.getTelefone();
        }
        if (pacienteDto.getEndereco() != null) {
            this.endereco.atualizarInformacoesEnderecos(pacienteDto.getEndereco());
        }

    }
	
}
