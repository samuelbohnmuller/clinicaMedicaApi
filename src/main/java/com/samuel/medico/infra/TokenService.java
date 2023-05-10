package com.samuel.medico.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.samuel.medico.model.usuario.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}") //Lê do aplication.properties o valor.
	private String secret;
	
	public String gerarToken(Usuario usuario) { 
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API clinica")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }        
    }
	
	public String getSubject(String tokenJWT) { //Passa um token e verifica se está válido, devolvendo o usuário.
        try {
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.require(algoritmo)
                                .withIssuer("API clinica")
                                .build()
                                .verify(tokenJWT)
                                .getSubject();
        } catch (JWTVerificationException exception) {
                throw new RuntimeException("Token JWT inválido ou expirado!");
        }
}
	
	private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(14).toInstant(ZoneOffset.of("-03:00"));
    }

}
