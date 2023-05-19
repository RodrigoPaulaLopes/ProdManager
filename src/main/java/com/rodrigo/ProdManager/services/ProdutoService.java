package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Produto;
import com.rodrigo.ProdManager.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Page<Produto> findAll(Pageable paginacao){
        return produtoRepository.findAll(paginacao);
    }

    public Produto findById(long id){
        return produtoRepository.findById(id).get();
    }
}
