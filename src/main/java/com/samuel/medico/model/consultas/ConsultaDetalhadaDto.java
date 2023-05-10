package com.samuel.medico.model.consultas;

import java.time.LocalDateTime;

public class ConsultaDetalhadaDto {

	Long id;
	Long idMedico;
    Long idPaciente;
    LocalDateTime data;
    
	public ConsultaDetalhadaDto() {
	}

	public ConsultaDetalhadaDto(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
		this.id = id;
		this.idMedico = idMedico;
		this.idPaciente = idPaciente;
		this.data = data;
	}
	
	public ConsultaDetalhadaDto(Consulta consulta) { //Depois de salvar dados no banco, quero mostrar dados para o usuário, mas como não posso mostrar a entidade, a entidade passa seus valores para o DTO, para o DTO poder mostrar.
		this.id = consulta.getId();
		this.idMedico = consulta.getMedico().getId(); //Para receber o id da entidade Medico.
		this.idPaciente = consulta.getPaciente().getId();
		this.data = consulta.getData();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
    
	
	
}
