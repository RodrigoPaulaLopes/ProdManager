package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Cliente;

public record InserirClientePedidoDTO(long id) {


    public InserirClientePedidoDTO(Cliente cliente){
        this(cliente.getId());
    }
}
