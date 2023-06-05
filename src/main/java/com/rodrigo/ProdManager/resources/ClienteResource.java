package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.dtos.*;
import com.rodrigo.ProdManager.services.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.net.URI;

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

    @PostMapping
    @Transactional
    public ResponseEntity<ListarClientesDTO> create(@Valid @RequestBody InserirClienteDTO dados, UriComponentsBuilder builder){

        var cliente = clienteService.create(dados);
        var uri = builder.path("/clientes/{id}").buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);

    }
    @PutMapping
    @Transactional
    public ResponseEntity<ListarClientesDTO> update(@Valid @RequestBody AtualizarClienteDTO dados){
        var categoria = clienteService.update(dados);
        return ResponseEntity.ok().body(categoria);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity delete(@PathVariable long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/foto")
    @Transactional
    public ResponseEntity<ListarClientesDTO> uploadFile(@RequestParam("file") MultipartFile file){
        URI uri = clienteService.enviarFoto(file);

        return ResponseEntity.created(uri).build();

    }

}
