package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.ItemPedidoPk;

public record PedidoProdutoDTO(Long id) {


    public PedidoProdutoDTO(ItemPedidoPk itemPedidoPk){
        this(itemPedidoPk.getProduto().getId());
    }
}
