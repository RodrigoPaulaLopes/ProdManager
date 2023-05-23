package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Pedido;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ListarPedidoDTO(Integer id, Date instante, ListarPagamentoDTO pagamento, ListarEnderecoDTO endereco, ListarClientesDTO cliente, Set<ListarItemPedidoDTO> pedidos) {

    public ListarPedidoDTO(Pedido pedido){
        this(pedido.getId(), pedido.getInstante(), new ListarPagamentoDTO(pedido.getPagamento()), new ListarEnderecoDTO(pedido.getEnderecoEntrega()), new ListarClientesDTO(pedido.getCliente()), pedido.getItems().stream().map(ListarItemPedidoDTO::new).collect(Collectors.toSet()));
    }
}
