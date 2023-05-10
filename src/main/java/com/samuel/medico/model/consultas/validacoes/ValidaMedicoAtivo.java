package com.samuel.medico.model.consultas.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;
import com.samuel.medico.model.consultas.ValidacaoException;
import com.samuel.medico.repository.MedicoRepository;

@Component
public class ValidaMedicoAtivo implements ValidaAgendamentoConsulta{
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void validar(ConsultasAgendamentoDto consultasAgendamentoDto){
		 if(consultasAgendamentoDto.getIdMedico() == null) {
			 return; //Cai fora do método.
		 }
		 
		 var medicoAtivo = medicoRepository.findAtivoById(consultasAgendamentoDto.getIdMedico()); //Busca se o objeto passado tem campo ativo como true(buscando pelo id do objeto passado no body).
		 
		 if(!medicoAtivo) {
			 throw new ValidacaoException("Médico não disponível.");
		 }
		  
	}
}
