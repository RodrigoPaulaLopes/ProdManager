package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Categoria;
import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.dtos.*;
import com.rodrigo.ProdManager.enums.TipoCliente;
import com.rodrigo.ProdManager.repository.CidadeRepository;
import com.rodrigo.ProdManager.repository.ClienteRepository;
import com.rodrigo.ProdManager.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;



    public Page<ListarClientesDTO> findAll(Pageable paginacao){
        return clienteRepository.findAll(paginacao).map(ListarClientesDTO::new);
    }

    public ListarClientesDTO findById(Long id){
        return new ListarClientesDTO(clienteRepository.getReferenceById(id));
    }

    public ListarClientesDTO update(AtualizarClienteDTO dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizar(dados);
        return new ListarClientesDTO(clienteRepository.save(cliente));
    }

    public void delete(long id) {
        var cliente = clienteRepository.getReferenceById(id);
        clienteRepository.delete(cliente);
    }
}
