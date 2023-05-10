package com.samuel.medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.medico.model.consultas.AgendamentoConsultaService;
import com.samuel.medico.model.consultas.CancelamentoConsultaDto;
import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("consultas")
public class ConsultasController {

	@Autowired
	AgendamentoConsultaService agendamentoConsultaService;
	
	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid ConsultasAgendamentoDto consultasDto) {
		var dadosDto = agendamentoConsultaService.agendar(consultasDto);
		
		return ResponseEntity.ok(dadosDto);
	}
	
	@DeleteMapping
	@Transactional
	public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDto cancelamentoConsultaDto) {
		agendamentoConsultaService.cancelar(cancelamentoConsultaDto);
		
		return ResponseEntity.noContent().build();
	}

}