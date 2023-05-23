package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.ItemPedido;

import java.util.List;

public record ListarItemPedidoDTO(ListarProdutoDTO produtos, Double desconto, Integer quantidade, Double preco) {

    public ListarItemPedidoDTO(ItemPedido itemPedido){
        this(new ListarProdutoDTO(itemPedido.getProduto()), itemPedido.getDesconto(), itemPedido.getQuantidade(), itemPedido.getPreco());
    }
}
