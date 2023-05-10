package com.samuel.medico.model.consultas;

import java.time.LocalDateTime;

import com.samuel.medico.model.medico.Especialidade;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class ConsultasAgendamentoDto {

	Long idMedico;
    @NotNull
    Long idPaciente;
    @NotNull @Future //Data no futuro apenas.
    LocalDateTime data;
    Especialidade especialidade;
    
	public ConsultasAgendamentoDto() {
	}

	public ConsultasAgendamentoDto(Long idMedico, Long idPaciente, LocalDateTime data, Especialidade especialidade) {
		this.idMedico = idMedico;
		this.idPaciente = idPaciente;
		this.data = data;
		this.especialidade = especialidade;
	}
	
	public Long getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
    
}
