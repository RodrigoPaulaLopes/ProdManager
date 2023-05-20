package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Categoria;

import java.util.List;

public record ListarCategoriaProdutosDTO(Long id, String nome, List<ListarProdutoDTO> produtos) {

    public ListarCategoriaProdutosDTO(Categoria categoria){
        this(categoria.getId(), categoria.getNome(), categoria.getProdutos().stream().map(ListarProdutoDTO::new).toList());
    }
}
