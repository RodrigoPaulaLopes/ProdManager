package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Categoria;
import com.rodrigo.ProdManager.repository.CategoriaRepository;
import com.rodrigo.ProdManager.resources.CategoriaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {


    @Autowired
    private CategoriaRepository categoriaRepository;


    public Page<Categoria> findAll(Pageable paginacao){
        return categoriaRepository.findAll(paginacao);
    }
}
