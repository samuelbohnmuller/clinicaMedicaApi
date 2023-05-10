package com.samuel.medico.model.medico;

import com.samuel.medico.model.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity(name = "Medico")
@Table(name = "medicos")
@EqualsAndHashCode(of = "id") 
public class Medico {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;
    private String email;
    private String crm;
	private String telefone;
	@Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded 
    private Endereco endereco;
    private Boolean ativo;
	
	public Medico(MedicoDto medico) {
		this.ativo = true;
		this.nome = medico.getNome();
		this.email = medico.getEmail();
		this.crm = medico.getCrm();
		this.telefone = medico.getTelefone();
		this.especialidade = medico.getEspecialidade();
		this.endereco = new Endereco(medico.getEndereco());
	}

	public Medico() {
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
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void atualizarInformacoesMedicos(AtualizaMedicoDto medico) {
		if (medico.getNome() != null) {
            this.nome = medico.getNome();
        }
        if (medico.getTelefone() != null) {
            this.telefone = medico.getTelefone();
        }
        if (medico.getEndereco() != null) {
            this.endereco.atualizarInformacoesEnderecos(medico.getEndereco());
        }
		
	}

	public void excluir() {
		this.ativo = false;
	}

}
