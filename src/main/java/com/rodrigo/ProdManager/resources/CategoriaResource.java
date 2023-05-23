package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.dtos.InserirCategoriaDTO;
import com.rodrigo.ProdManager.dtos.ListarCategoriaDTO;
import com.rodrigo.ProdManager.dtos.ListarCategoriaProdutosDTO;
import com.rodrigo.ProdManager.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;
    @GetMapping
    public ResponseEntity<Page<ListarCategoriaProdutosDTO>> findAll(Pageable paginacao){
        return ResponseEntity.ok().body(categoriaService.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarCategoriaProdutosDTO> findById(@PathVariable long id){
        return ResponseEntity.ok().body(categoriaService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListarCategoriaDTO> create(@RequestBody InserirCategoriaDTO dados, UriComponentsBuilder builder){
        var categoria = categoriaService.create(dados);
        var uri = builder.path("/categorias/{id}").buildAndExpand(categoria.id()).toUri();
        return ResponseEntity.created(uri).body(categoria);

    }
}
