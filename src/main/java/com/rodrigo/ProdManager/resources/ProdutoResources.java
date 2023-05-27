package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.dtos.ListarProdutosCategoriaDTO;
import com.rodrigo.ProdManager.resources.utils.URL;
import com.rodrigo.ProdManager.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<Page<ListarProdutosCategoriaDTO>> findLikeName(@RequestParam("nome") String nome,  @RequestParam("categorias") String categorias,  Pageable paginacao){
        List<Long> ids = URL.decodeUrl(categorias);
        String novoNome = URL.decodeString(nome);
        return ResponseEntity.ok().body(produtoService.search(novoNome, ids, paginacao));
    }
}
