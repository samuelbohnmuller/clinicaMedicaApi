package com.samuel.medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.samuel.medico.model.medico.AtualizaMedicoDto;
import com.samuel.medico.model.medico.Medico;
import com.samuel.medico.model.medico.MedicoDetalhadoDto;
import com.samuel.medico.model.medico.MedicoDto;
import com.samuel.medico.model.medico.listaMedicosDto;
import com.samuel.medico.repository.MedicoRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid MedicoDto medico, UriComponentsBuilder uriBuilder) {
		var medicos = medicoRepository.save(new Medico(medico));
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicos.getId()).toUri(); //Devolve o cabeçalho location com a URL.
		
		return ResponseEntity.created(uri).body(new MedicoDetalhadoDto(medicos)); //Devolve código 201, cabeçalho e o objeto criado no corpo da resposta.
	}
	
	@GetMapping
	public ResponseEntity<Page<listaMedicosDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
		var page =  medicoRepository.findAllByAtivoTrue(pageable).map(listaMedicosDto::new); //Cria um novo objeto listaMedicosDto a cada objeto Medico e retorna isso em uma lista.
		
		return ResponseEntity.ok(page); //Devolve código 200, com as informações buscadas.
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid AtualizaMedicoDto medico) {
		var medicos = medicoRepository.getReferenceById(medico.getId());
		medicos.atualizarInformacoesMedicos(medico);
		
		return ResponseEntity.ok(new MedicoDetalhadoDto(medicos));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var medicos = medicoRepository.getReferenceById(id);
		medicos.excluir();
		//medicoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity listarPorId(@PathVariable Long id) {
		var medicos = medicoRepository.getReferenceById(id);
		
		return ResponseEntity.ok(new MedicoDetalhadoDto(medicos));
	}
}
