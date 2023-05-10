package com.samuel.medico.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

	public Endereco(EnderecoDto enderecoDto) {
		this.logradouro = enderecoDto.logradouro;
		this.bairro = enderecoDto.bairro;
		this.cep = enderecoDto.cep;
		this.numero = enderecoDto.numero;
		this.complemento = enderecoDto.complemento;
		this.cidade = enderecoDto.cidade;
		this.uf = enderecoDto.uf;
	}

	public Endereco() {
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void atualizarInformacoesEnderecos(EnderecoDto enderecoDto) {
		if (enderecoDto.getLogradouro() != null) {
            this.logradouro = enderecoDto.getLogradouro();
        }
        if (enderecoDto.getBairro() != null) {
            this.bairro = enderecoDto.getBairro();
        }
        if (enderecoDto.getCep() != null) {
            this.cep = enderecoDto.getCep();
        }
        if (enderecoDto.getUf() != null) {
            this.uf = enderecoDto.getUf();
        }
        if (enderecoDto.getCidade() != null) {
            this.cidade = enderecoDto.getCidade();
        }
        if (enderecoDto.getNumero() != null) {
            this.numero = enderecoDto.getNumero();
        }
        if (enderecoDto.getComplemento() != null) {
            this.complemento = enderecoDto.getComplemento();
        }
		
	}
	
}
