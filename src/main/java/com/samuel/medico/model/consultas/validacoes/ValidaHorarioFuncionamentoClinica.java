package com.samuel.medico.model.consultas.validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;
import com.samuel.medico.model.consultas.ValidacaoException;

@Component
public class ValidaHorarioFuncionamentoClinica implements ValidaAgendamentoConsulta{

	public void validar(ConsultasAgendamentoDto consultasAgendamentoDto) {
		var dataConsulta = consultasAgendamentoDto.getData();
		
		var diaDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY); //Verifica se o dia passado no corpo da requisição é domingo.
		var antesAberturaClinica = dataConsulta.getHour() < 7; //Retorna true ou false através de variável que armazena a hora menor que 7 no objeto vindo do body, pois a clínica abre as 7. 
		var depoisAberturaClinica = dataConsulta.getHour() > 18;
		
		if(diaDomingo || antesAberturaClinica || depoisAberturaClinica) {
			throw new ValidacaoException("Somente consultas de segunda a sábado ás 7h as 18h.");
		}
	}
}
