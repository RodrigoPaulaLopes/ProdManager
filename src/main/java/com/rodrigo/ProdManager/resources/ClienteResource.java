package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.dtos.AtualizarCategoriaDTO;
import com.rodrigo.ProdManager.dtos.AtualizarClienteDTO;
import com.rodrigo.ProdManager.dtos.ListarCategoriaDTO;
import com.rodrigo.ProdManager.dtos.ListarClientesDTO;
import com.rodrigo.ProdManager.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<ListarClientesDTO> findAll(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ListarClientesDTO> update(@Valid @RequestBody AtualizarClienteDTO dados){
        var categoria = clienteService.update(dados);
        return ResponseEntity.ok().body(categoria);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
