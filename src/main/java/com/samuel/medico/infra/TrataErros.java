package com.samuel.medico.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.samuel.medico.model.consultas.ValidacaoException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice 
public class TrataErros {

	@ExceptionHandler(EntityNotFoundException.class) 
	public ResponseEntity erro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity erro400(MethodArgumentNotValidException exceptionCapturada) {
		var erros = exceptionCapturada.getFieldErrors(); //Pega erro dos campos.
		
		return ResponseEntity.badRequest().body(erros.stream().map(DadorErros::new).toList());
	}
	
	@ExceptionHandler(ValidacaoException.class) 
	public ResponseEntity erroValidacoes(ValidacaoException exceptionCapturada) {
		
		return ResponseEntity.badRequest().body(exceptionCapturada.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }
	
	@ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErroBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
	
	public class DadorErros {
		
		String campo;
		String mensagem;
		
		public String getCampo() {
			return campo;
		}
		public void setCampo(String campo) {
			this.campo = campo;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		
		public DadorErros(FieldError fieldError) {
			this.campo = fieldError.getField(); 
			this.mensagem = fieldError.getDefaultMessage(); 
		}
		
		public DadorErros() {
		}

	}
	
}
