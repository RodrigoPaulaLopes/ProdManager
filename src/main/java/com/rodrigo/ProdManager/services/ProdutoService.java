package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Categoria;
import com.rodrigo.ProdManager.dtos.ListarCategoriaDTO;
import com.rodrigo.ProdManager.dtos.ListarProdutosCategoriaDTO;
import com.rodrigo.ProdManager.repository.CategoriaRepository;
import com.rodrigo.ProdManager.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Page<ListarProdutosCategoriaDTO> findAll(Pageable paginacao){
        return produtoRepository.findAll(paginacao).map(ListarProdutosCategoriaDTO::new);
    }

    public ListarProdutosCategoriaDTO findById(long id){
        return new ListarProdutosCategoriaDTO(produtoRepository.getReferenceById(id));
    }

    public Page<ListarProdutosCategoriaDTO> search(String nome, List<Long> ids, Pageable paginacao){

        List<Categoria> categorias = categoriaRepository.findAllById(ids);

        return produtoRepository.search(nome, categorias, paginacao).map(ListarProdutosCategoriaDTO::new);
    }
}
