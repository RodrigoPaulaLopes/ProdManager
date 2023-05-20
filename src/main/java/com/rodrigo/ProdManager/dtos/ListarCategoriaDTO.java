package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Categoria;

public record ListarCategoriaDTO(long id, String nome) {
    public ListarCategoriaDTO(Categoria categoria){
        this(categoria.getId(), categoria.getNome());
    }
}
