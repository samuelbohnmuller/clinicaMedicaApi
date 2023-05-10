package com.samuel.medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.medico.infra.TokenJwtDto;
import com.samuel.medico.infra.TokenService;
import com.samuel.medico.model.usuario.AutenticacaoDto;
import com.samuel.medico.model.usuario.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
    private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDto autenticacaoDto) { 
        var tokenAuthenticacao = new UsernamePasswordAuthenticationToken(autenticacaoDto.getLogin(), autenticacaoDto.getSenha()); //Pega os valores preenchidos pelo usuário no corpo.
        var authenticacao = manager.authenticate(tokenAuthenticacao); //Autentica os valores preenchidos.

        var tokenJwt = tokenService.gerarToken((Usuario) authenticacao.getPrincipal()); //Gera o token.
        
        return ResponseEntity.ok(new TokenJwtDto(tokenJwt)); //Devolve o token.
    }
	
}
