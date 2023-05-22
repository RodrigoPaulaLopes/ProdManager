package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Cidade;

public record ListarCidadeDTO(String nome, ListarEstadoDTO estado) {

    public ListarCidadeDTO(Cidade cidade){
        this(cidade.getNome(), new ListarEstadoDTO(cidade.getEstado()));
    }
}
