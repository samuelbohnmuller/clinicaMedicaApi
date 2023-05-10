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

import com.samuel.medico.model.pacientes.AtualizacaoPacienteDto;
import com.samuel.medico.model.pacientes.ListaPacientesDto;
import com.samuel.medico.model.pacientes.Paciente;
import com.samuel.medico.model.pacientes.PacienteDetalhadoDto;
import com.samuel.medico.model.pacientes.PacienteDto;
import com.samuel.medico.repository.PacienteRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearer-key") //Libera adição de token nos endpoints da classe.
@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
    private PacienteRepository pacienteRepository;
	
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteDto paciente, UriComponentsBuilder uriBuilder) {
    	var pacientes = pacienteRepository.save(new Paciente(paciente));
    	
    	var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacientes.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDetalhadoDto(pacientes));
    }
    
    @GetMapping
    public ResponseEntity<Page<ListaPacientesDto>> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable){
    	var page = pacienteRepository.findAllByAtivoTrue(pageable).map(ListaPacientesDto::new);
    	return ResponseEntity.ok(page);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoPacienteDto atualizacaoDto) {
    	var paciente = pacienteRepository.getReferenceById(atualizacaoDto.getId());
    	paciente.atualizarInformacoes(atualizacaoDto); //Passo o objeto com os atributos passado no corpo da requisição, para os atributos da entidade Paciente, e o transactional automáticamente atualiza no banco de dados.
    
    	return ResponseEntity.ok(new PacienteDetalhadoDto(paciente));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
    	var paciente = pacienteRepository.getReferenceById(id);
    	paciente.excluir();
    	
    	return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        
        return ResponseEntity.ok(new PacienteDetalhadoDto(paciente));
    }

}
