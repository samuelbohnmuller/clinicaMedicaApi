package com.samuel.medico.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samuel.medico.model.medico.Especialidade;
import com.samuel.medico.model.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

	Page<Medico> findAllByAtivoTrue(Pageable pageable);
	
	/*Apenas 1 médico aleatório, que seja ativo, que a especialidade seja igual a passada por ().
	Subconsulta na tabela de Consulta, onde não busca médicos(pelo id) que tenha data de consulta igual a passada por ()
	e campo motivoCancelamento seja nulo.*/
	@Query("SELECT medico FROM Medico medico "
			+ "WHERE medico.ativo = 1 "
			+ "AND medico.especialidade = :medicoEspecialidade " 
			+ "AND medico.id not in("
			+ "SELECT consulta.medico.id "
			+ "FROM Consulta consulta "
			+ "WHERE consulta.data = :dataConsulta "
			+ "AND consulta.motivoCancelamento is null) "
			+ "ORDER BY rand() LIMIT 1")  
	Medico medicoLivrePorDataNaEspecialidade(Especialidade medicoEspecialidade, LocalDateTime dataConsulta);

	@Query("SELECT medico.ativo "
            + "FROM Medico medico "
            + "WHERE medico.id = :idMedico")
	Boolean findAtivoById(Long idMedico); //Pega somente o campo ativo, pelo id passado por ().

}
