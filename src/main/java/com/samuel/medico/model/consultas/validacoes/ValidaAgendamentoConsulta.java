package com.samuel.medico.model.consultas.validacoes;

import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;

public interface ValidaAgendamentoConsulta {

	public void validar(ConsultasAgendamentoDto consultasDto); //Toda classe que implementa essa interface precisa implementar esse método, do seu jeito.
	
}
