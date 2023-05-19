package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.domain.Categoria;
import com.rodrigo.ProdManager.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;
    @GetMapping
    public ResponseEntity<Page<Categoria>> findAll(Pageable paginacao){
        return ResponseEntity.ok().body(categoriaService.findAll(paginacao));
    }
}
