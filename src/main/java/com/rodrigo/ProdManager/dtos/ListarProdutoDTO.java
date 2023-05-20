package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Produto;

public record ListarProdutoDTO(long id, String nome, Double preco) {

    public ListarProdutoDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco());
    }
}
