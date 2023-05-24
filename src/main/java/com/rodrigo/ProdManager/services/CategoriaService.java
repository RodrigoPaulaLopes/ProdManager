package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Categoria;
import com.rodrigo.ProdManager.dtos.AtualizarCategoriaDTO;
import com.rodrigo.ProdManager.dtos.InserirCategoriaDTO;
import com.rodrigo.ProdManager.dtos.ListarCategoriaDTO;
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


    public Page<ListarCategoriaProdutosDTO> findAll(Pageable paginacao) {
        return categoriaRepository.findAll(paginacao).map(ListarCategoriaProdutosDTO::new);

    }

    public ListarCategoriaProdutosDTO findById(long id) {
        return new ListarCategoriaProdutosDTO(categoriaRepository.getReferenceById(id));
    }

    public ListarCategoriaDTO create(InserirCategoriaDTO dados) {
        var categoria = new Categoria(dados);
        return new ListarCategoriaDTO(categoriaRepository.save(categoria));
    }

    public ListarCategoriaDTO update(AtualizarCategoriaDTO dados) {
        var categoria = categoriaRepository.getReferenceById(dados.id());

        categoria.setNome(dados.nome());

        return new ListarCategoriaDTO(categoriaRepository.save(categoria));
    }

    public void delete(long id) {
        var categoria = categoriaRepository.getReferenceById(id);
       categoriaRepository.delete(categoria);
    }
}
