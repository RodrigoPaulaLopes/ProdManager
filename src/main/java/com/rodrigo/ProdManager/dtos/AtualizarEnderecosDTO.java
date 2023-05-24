package com.rodrigo.ProdManager.dtos;

public record AtualizarEnderecosDTO(String logradouro, String numero, String complemento, String bairro, String cep, AtualizarCidadeDTO cidade) {
}
