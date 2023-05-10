package com.samuel.medico.model.consultas;

import java.time.LocalDateTime;

import com.samuel.medico.model.medico.Medico;
import com.samuel.medico.model.pacientes.Paciente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Consulta")
@Table(name = "consultas")
public class Consulta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "medico_id")
    private Medico medico; //O médico pode estar acossiado a várias consultas, mas uma consulta tem apenas 1 médico.
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime data;
    @Column(name = "motivo_cancelamento") @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;
    
	public Consulta() {
	}

	public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime data, MotivoCancelamento motivoCancelamento) {
		this.id = id;
		this.medico = medico;
		this.paciente = paciente;
		this.data = data;
		this.motivoCancelamento = motivoCancelamento;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public MotivoCancelamento getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public void cancelar(MotivoCancelamento motivo) {
		this.motivoCancelamento = motivo;
		
	}
    
}
