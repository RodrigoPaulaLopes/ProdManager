package com.rodrigo.ProdManager.exceptions;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Exceptions {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entidadeNaoEncotrada(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RecursoNaoEncontrado(HttpStatus.NOT_FOUND, "Recurso não encontrado"));
    }

    public record RecursoNaoEncontrado(HttpStatus code, String mensagem){

    }
}
