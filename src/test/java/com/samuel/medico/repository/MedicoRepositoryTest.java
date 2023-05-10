package com.samuel.medico.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.samuel.medico.model.medico.Especialidade;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Usará meu banco de dados do projeto.
@DataJpaTest //Testa interface repository.
public class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deve devolver null quando o único médico cadastrado não está disponível na data.")
    void testMedicoLivrePorDataNaEspecialidadeCenario1() {
        
        var proximaSegundaDezHoras = LocalDate.now() //Pega sempre a próxima segunda feira as 10h.
        .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
        .atTime(10,0); 

        var medicoLivre = medicoRepository.medicoLivrePorDataNaEspecialidade(Especialidade.CARDIOLOGIA, proximaSegundaDezHoras);

        assertThat(medicoLivre).isNull();
    }
 
}
