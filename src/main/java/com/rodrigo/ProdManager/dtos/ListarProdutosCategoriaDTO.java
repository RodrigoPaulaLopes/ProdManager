package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.ItemPedido;
import com.rodrigo.ProdManager.domain.Produto;

import java.util.List;

public record ListarProdutosCategoriaDTO(long id, String nome, Double preco, List<ListarCategoriaDTO> categorias, List<ListarItemPedidoDTO> itemPedidos) {

    public ListarProdutosCategoriaDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getCategorias().stream().map(ListarCategoriaDTO::new).toList(), produto.getItems().stream().map(ListarItemPedidoDTO::new).toList());
    }
}
