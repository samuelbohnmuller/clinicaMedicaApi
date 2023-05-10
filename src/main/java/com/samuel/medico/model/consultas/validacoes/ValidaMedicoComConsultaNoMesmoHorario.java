package com.samuel.medico.model.consultas.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;
import com.samuel.medico.model.consultas.ValidacaoException;
import com.samuel.medico.repository.ConsultaRepository;

@Component
public class ValidaMedicoComConsultaNoMesmoHorario implements ValidaAgendamentoConsulta{

	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void validar(ConsultasAgendamentoDto consultasAgendamentoDto) {
		var medicoConsultaNesseHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(consultasAgendamentoDto.getIdMedico(), consultasAgendamentoDto.getData());
	
		if(medicoConsultaNesseHorario) { 
			throw new ValidacaoException("O médico já possui consulta nessa data.");
		}
	}
}
