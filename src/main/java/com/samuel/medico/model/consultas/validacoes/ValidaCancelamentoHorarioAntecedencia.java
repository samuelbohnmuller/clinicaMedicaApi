package com.samuel.medico.model.consultas.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samuel.medico.model.consultas.CancelamentoConsultaDto;
import com.samuel.medico.model.consultas.ValidacaoException;
import com.samuel.medico.repository.ConsultaRepository;

@Component
public class ValidaCancelamentoHorarioAntecedencia implements ValidaCancelamentoConsulta{

	@Autowired
	ConsultaRepository consultaRepository;
	
	@Override
	public void validar(CancelamentoConsultaDto cancelamentoConsultaDto) {
		var consulta = consultaRepository.getReferenceById(cancelamentoConsultaDto.getIdConsulta());
		var agora = LocalDateTime.now();
		var diferencaHoras = Duration.between(agora, consulta.getData()).toHours(); //Diferença em horas do momento agora, para a requisição do cancelamento da consulta pelo usuário.
	
		if(diferencaHoras < 24) {
			throw new ValidacaoException("Cancelamento somente com 24h de antecedência.");
		}
	}

}
