package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.ItemPedido;

public record InserirItemsPedidoDTO(Integer quantidade, PedidoProdutoDTO idProduto) {


    public InserirItemsPedidoDTO(ItemPedido itemPedido){
        this(itemPedido.getQuantidade(), new PedidoProdutoDTO(itemPedido.getId()));
    }
}
