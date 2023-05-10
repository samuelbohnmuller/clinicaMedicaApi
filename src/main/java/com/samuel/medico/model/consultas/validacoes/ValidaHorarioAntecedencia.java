package com.samuel.medico.model.consultas.validacoes;

import java.time.Duration;

import org.springframework.stereotype.Component;

import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;
import com.samuel.medico.model.consultas.ValidacaoException;

@Component //Componente genérico do Spring que deve ser carregado.
public class ValidaHorarioAntecedencia implements ValidaAgendamentoConsulta{

	public void validar(ConsultasAgendamentoDto consultasAgendamentoDto) {
		var dataConsulta = consultasAgendamentoDto.getData();
		
		var agora = dataConsulta.toLocalTime().now(); //Momento agora.
		var antecedenciaMinutos = Duration.between(agora, dataConsulta) .toMinutes(); //Pega o momento agora e ve quantos minutos dá até o horário que o usuário digitou.
		
		
		if(antecedenciaMinutos < 30) {
			throw new ValidacaoException("Consulta deve ter antecedência de 30 minutos.");
		}
	}
}
