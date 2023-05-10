package com.samuel.medico.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.samuel.medico.model.consultas.AgendamentoConsultaService;
import com.samuel.medico.model.consultas.ConsultaDetalhadaDto;
import com.samuel.medico.model.consultas.ConsultasAgendamentoDto;
import com.samuel.medico.model.medico.Especialidade;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters //Injeta jacksonTester.
@WithMockUser //Cria usuário para ser autenticado.
@AutoConfigureMockMvc
@SpringBootTest //Testa classes controllers.
public class ConsultasControllerTest {

    @MockBean //Mock de classe Service.
    private AgendamentoConsultaService agendamentoConsultaService;

    @Autowired
    JacksonTester<ConsultasAgendamentoDto> consultasAgendamentoDtoJson; //Simula o JSON.

    @Autowired
    private JacksonTester<ConsultaDetalhadaDto> dadosDetalhamentoConsultaJson;

    @Autowired
    private MockMvc mock; //Simula requisições HTTP.

    @Test
    @DisplayName("Deve devolver código HTTP 400, quando as informações estão incorretas.")
    void testAgendar() throws Exception {

        var resposta = mock.perform(post("/consultas")) //Requisição post para esse endpoint.
            .andReturn().getResponse();

        assertThat(resposta.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value()); //Verifica se o status é 400.
    }

    @Test
    @DisplayName("Deve devolver codigo HTTP 200 quando informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {

        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA; //Cria na mão uma nova especialidade.

        var consultaDetalhadaDto = new ConsultaDetalhadaDto(null, 1l, 1l, data);
        when(agendamentoConsultaService.agendar(any())).thenReturn(consultaDetalhadaDto); //Quando o Service com o método agendar for chamado, não importando o parâmetro(any), deve devolver um objeto do tipo ConsultaDetalhadaDto.

        var response = mock
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON) //Leva o cabeçalho JSON.
                                .content(consultasAgendamentoDtoJson.write(
                                        new ConsultasAgendamentoDto(1l, 1l, data, especialidade)
                                ).getJson()) //Converte para JSON o novo objeto.
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value()); //Verifica status 200.

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(consultaDetalhadaDto).getJson(); //Devolve o JSON da requisição.

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado); //Verifica se o JSON no corpo da requisição é o esperado(é o mesmo criado no mock).
    }
}
