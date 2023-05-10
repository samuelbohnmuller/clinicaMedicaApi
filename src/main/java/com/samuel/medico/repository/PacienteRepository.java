package com.samuel.medico.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samuel.medico.model.pacientes.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	Page<Paciente> findAllByAtivoTrue(Pageable pageable);

	@Query("SELECT paciente.ativo "
			+ "FROM Paciente paciente "
			+ "WHERE paciente.id = :idPaciente")
	Boolean findAtivoById(Long idPaciente); //Mostra somente o campo ativo do objeto que tem o id passado no método.

}
