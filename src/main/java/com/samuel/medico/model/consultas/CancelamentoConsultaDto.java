package com.samuel.medico.model.consultas;

import jakarta.validation.constraints.NotNull;

public class CancelamentoConsultaDto {

	@NotNull
    Long idConsulta;
    @NotNull
    MotivoCancelamento motivo;
    
	public CancelamentoConsultaDto() {
	}
	
	public CancelamentoConsultaDto(Long idConsulta, MotivoCancelamento motivo) {
		this.idConsulta = idConsulta;
		this.motivo = motivo;
	}
	public Long getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}
	public MotivoCancelamento getMotivo() {
		return motivo;
	}
	public void setMotivo(MotivoCancelamento motivo) {
		this.motivo = motivo;
	}
    
}
