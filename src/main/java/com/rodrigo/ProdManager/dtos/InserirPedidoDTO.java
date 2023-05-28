package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Pagamento;
import com.rodrigo.ProdManager.domain.Pedido;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record InserirPedidoDTO(InserirClientePedidoDTO idCliente, InserirEnderecoEntregaDTO enderecoEntrega, Pagamento pagamento, List<InserirItemsPedidoDTO> items) {

    public InserirPedidoDTO(Pedido pedido){
        this(new InserirClientePedidoDTO(pedido.getCliente().getId()), new InserirEnderecoEntregaDTO(pedido.getEnderecoEntrega().getId()), pedido.getPagamento(), pedido.getItems().stream().map(InserirItemsPedidoDTO::new).toList());
    }
}
