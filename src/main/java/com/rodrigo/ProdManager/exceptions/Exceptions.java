package com.rodrigo.ProdManager.exceptions;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import jakarta.persistence.EntityNotFoundException;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Exceptions {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entidadeNaoEncotrada(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Recurso não encontrado"));
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity integrityError(){
        return ResponseEntity.badRequest().body(new ErrorDTO("Você não pode deletar um objeto que tenha outros objetos associados a ele."));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodNotValid(MethodArgumentNotValidException ex){
        var error = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(Validacao::new).toList());
    }
    public record ErrorDTO(String mensagem){

    }
    public record Validacao(String campo, String mensagem){
        public Validacao(FieldError campos){
            this(campos.getField(), campos.getDefaultMessage());
        }
    }
}
