package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.dtos.ListarClientesDTO;
import com.rodrigo.ProdManager.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<ListarClientesDTO>> findAll(Pageable paginacao) {
        return ResponseEntity.ok().body(clienteService.findAll(paginacao));
    }

}
