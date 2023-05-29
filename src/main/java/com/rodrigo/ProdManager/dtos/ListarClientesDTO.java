package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.enums.TipoCliente;

import java.util.List;
import java.util.Set;

public record ListarClientesDTO(Long id, String nome, String email, String cpfOuCnpj, String senha, String tipo, List<ListarEnderecoDTO> enderecos, Set<String> telefones) {

    public ListarClientesDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpfOuCnpj(), cliente.getSenha(), cliente.getTipoCliente().getTipo(), cliente.getEnderecos().stream().map(ListarEnderecoDTO::new).toList(), cliente.getTelefones());
    }
}
