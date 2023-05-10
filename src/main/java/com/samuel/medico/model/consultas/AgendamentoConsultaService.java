package com.samuel.medico.model.consultas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.medico.model.consultas.validacoes.ValidaAgendamentoConsulta;
import com.samuel.medico.model.consultas.validacoes.ValidaCancelamentoConsulta;
import com.samuel.medico.model.medico.Medico;
import com.samuel.medico.repository.ConsultaRepository;
import com.samuel.medico.repository.MedicoRepository;
import com.samuel.medico.repository.PacienteRepository;

@Service
public class AgendamentoConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidaAgendamentoConsulta> validadoresAgendamento; //Lista validadores tem todas as classes que foram implementadas da interface ValidaAgendamentoConsulta, cada uma com seus métodos com diferentes implementações.

	@Autowired
	private List<ValidaCancelamentoConsulta> validadoresCancelamento;
	
	public ConsultaDetalhadaDto agendar(ConsultasAgendamentoDto consultasAgendamentoDto) { //Objeto preenchido pelo usuário no JSON.
		if(!pacienteRepository.existsById(consultasAgendamentoDto.getIdPaciente())) { //Se o id do paciente não existir.
			throw new ValidacaoException("Id do paciente não existe.");
		}
		
		if(consultasAgendamentoDto.getIdMedico() != null && !medicoRepository.existsById(consultasAgendamentoDto.getIdMedico())) { 
			throw new ValidacaoException("Id do medico não existe.");
		}
		
		validadoresAgendamento.forEach(validar -> validar.validar(consultasAgendamentoDto)); //Usa todos os método validar(que aplica validações de negócio) de todas as classes validadoras.
		
		var medico = escolherMedico(consultasAgendamentoDto);
		
		if(medico == null) {
			throw new ValidacaoException("Nessa data não há médico disponivel.");
		}
		
		var paciente = pacienteRepository.findById(consultasAgendamentoDto.getIdPaciente()).get(); //get() é para não retornar um Optional, mas sim o objeto do tipo Paciete.
		var consulta = new Consulta(null, medico, paciente, consultasAgendamentoDto.getData(), null);
		
		consultaRepository.save(consulta);
		
		return new ConsultaDetalhadaDto(consulta); //Retorna um objeto do tipo ConsultaDetalhadaDto, com os atributos carregados do banco, preenchidos pelo usuário no body da requisição.
	}

	private Medico escolherMedico(ConsultasAgendamentoDto consultasAgendamentoDto) {
		if(consultasAgendamentoDto.getIdMedico() != null) {
			return medicoRepository.getReferenceById(consultasAgendamentoDto.getIdMedico());
		}
		
		if(consultasAgendamentoDto.getEspecialidade() == null) {
			throw new ValidacaoException("Especialidade do médico é obrigatória.");
		}
		
		return medicoRepository.medicoLivrePorDataNaEspecialidade(consultasAgendamentoDto.getEspecialidade(), consultasAgendamentoDto.getData());
	}

	public void cancelar(CancelamentoConsultaDto cancelamentoConsultaDto) {
		if(!consultaRepository.existsById(cancelamentoConsultaDto.getIdConsulta())) {
			throw new ValidacaoException("Id da consulta não existe!");
		}
		
		validadoresCancelamento.forEach(validador -> validador.validar(cancelamentoConsultaDto));
		
		var consulta = consultaRepository.getReferenceById(cancelamentoConsultaDto.getIdConsulta());
		consulta.cancelar(cancelamentoConsultaDto.getMotivo());
		
	}
	
}
