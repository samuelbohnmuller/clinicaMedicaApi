package com.samuel.medico.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.samuel.medico.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //Carrega a classe como filtro quando o projeto é executado.
public class SecurityFilter extends OncePerRequestFilter{

	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var tokenJWT = recuperarToken(request);
		
		if (tokenJWT != null) { //Só pega chama token se tiver, para caso da URL de logar para obter o token.
			var subject = tokenService.getSubject(tokenJWT);
	        var usuario = repository.findByLogin(subject);

	        var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); 

	        SecurityContextHolder.getContext().setAuthentication(authentication); //Considera o usuário como logado.
	    }
		
		filterChain.doFilter(request, response); //Continua o fluxo da requisição.
    }
	
	private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization"); //Pega o token no cabeçalho Authorization.
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
	}
	
}
