package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Estado;

public record ListarEstadoDTO(String nome) {

    public ListarEstadoDTO(Estado estado){
        this(estado.getNome());
    }
}
