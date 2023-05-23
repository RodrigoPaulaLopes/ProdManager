package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Pedido;
import com.rodrigo.ProdManager.dtos.ListarPedidoDTO;
import com.rodrigo.ProdManager.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


    public Page<ListarPedidoDTO> findAll(Pageable paginacao) {
        return pedidoRepository.findAll(paginacao).map(ListarPedidoDTO::new);
    }

    public ListarPedidoDTO findById(Integer id) {
        return new ListarPedidoDTO(pedidoRepository.getReferenceById(id));
    }
}
