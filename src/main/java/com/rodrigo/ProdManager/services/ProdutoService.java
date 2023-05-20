package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.dtos.ListarProdutosCategoriaDTO;
import com.rodrigo.ProdManager.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Page<ListarProdutosCategoriaDTO> findAll(Pageable paginacao){
        return produtoRepository.findAll(paginacao).map(ListarProdutosCategoriaDTO::new);
    }

    public ListarProdutosCategoriaDTO findById(long id){
        return new ListarProdutosCategoriaDTO(produtoRepository.getReferenceById(id));
    }
}
