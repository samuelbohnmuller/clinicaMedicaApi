package com.samuel.medico.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.medico.model.consultas.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data); //Se existe um registro no banco na entidade Consulta com esse id e data que tenha o atributo do motivo como null.

	Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiraHora, LocalDateTime ultimaHora);

}
