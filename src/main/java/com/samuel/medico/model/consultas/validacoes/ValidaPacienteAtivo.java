package com.samuel.medico.model.consultas.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;
import com.samuel.medico.model.consultas.ValidacaoException;
import com.samuel.medico.repository.PacienteRepository;

@Component
public class ValidaPacienteAtivo implements ValidaAgendamentoConsulta{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void validar(ConsultasAgendamentoDto consultasAgendamentoDto) {
		var pacienteAtivo = pacienteRepository.findAtivoById(consultasAgendamentoDto.getIdPaciente());
		
		if(!pacienteAtivo) {
			throw new ValidacaoException("Paciente excluído");
		}
	}
}
