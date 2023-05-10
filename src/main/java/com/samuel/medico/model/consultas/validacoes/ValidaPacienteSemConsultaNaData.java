package com.samuel.medico.model.consultas.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;
import com.samuel.medico.model.consultas.ValidacaoException;
import com.samuel.medico.repository.ConsultaRepository;

@Component
public class ValidaPacienteSemConsultaNaData implements ValidaAgendamentoConsulta{

	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void validar(ConsultasAgendamentoDto consultasAgendamentoDto) {
		var primeiraHora = consultasAgendamentoDto.getData().withHour(7); //Verifica se tem consulta no dia, pelo horário de inicio e fim do dia passado no objeto.
		var ultimaHora = consultasAgendamentoDto.getData().withHour(18);
		var possuiConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(consultasAgendamentoDto.getIdPaciente(), primeiraHora, ultimaHora); //Se o id do objeto tem consulta entre os horários 7 e 18.
	
		if (possuiConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta nesse horário.");
        }
	}
}
