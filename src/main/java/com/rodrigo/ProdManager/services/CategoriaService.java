package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.dtos.ListarCategoriaProdutosDTO;
import com.rodrigo.ProdManager.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {


    @Autowired
    private CategoriaRepository categoriaRepository;


    public Page<ListarCategoriaProdutosDTO> findAll(Pageable paginacao){
       return categoriaRepository.findAll(paginacao).map(ListarCategoriaProdutosDTO::new);

    }

    public ListarCategoriaProdutosDTO findById(long id){
        return new ListarCategoriaProdutosDTO(categoriaRepository.getReferenceById(id));
    }
}
