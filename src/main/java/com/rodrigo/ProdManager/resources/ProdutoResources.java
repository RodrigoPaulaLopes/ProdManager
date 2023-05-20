package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.dtos.ListarProdutosCategoriaDTO;
import com.rodrigo.ProdManager.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoResources {


    @Autowired
    private ProdutoService produtoService;


    @GetMapping
    public ResponseEntity<Page<ListarProdutosCategoriaDTO>> findAll(Pageable paginacao){
        return ResponseEntity.ok().body(produtoService.findAll(paginacao));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ListarProdutosCategoriaDTO> findById(@PathVariable long id){
        return ResponseEntity.ok().body(produtoService.findById(id));
    }
}
