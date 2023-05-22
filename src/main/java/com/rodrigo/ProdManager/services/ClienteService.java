package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.dtos.ListarClientesDTO;
import com.rodrigo.ProdManager.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Page<ListarClientesDTO> findAll(Pageable paginacao){
        return clienteRepository.findAll(paginacao).map(ListarClientesDTO::new);
    }
}
