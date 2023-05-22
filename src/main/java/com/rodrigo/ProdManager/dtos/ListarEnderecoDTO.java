package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Endereco;

public record ListarEnderecoDTO(String logradouro, String numero, String complemento, String bairro, String cep, ListarCidadeDTO cidade) {

    public ListarEnderecoDTO(Endereco endereco){
        this(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCep(), new ListarCidadeDTO(endereco.getCidade()));
    }
}
