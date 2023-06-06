package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.enums.PerfilCliente;
import com.rodrigo.ProdManager.enums.TipoCliente;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record ListarClientesDTO(Long id, String nome, String email, String cpfOuCnpj, String senha, String tipo, Set<String> perfil, List<ListarEnderecoDTO> enderecos, Set<String> telefones,  String imagemUrl) {

    public ListarClientesDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpfOuCnpj(), cliente.getSenha(), cliente.getTipoCliente().getTipo(), cliente.getPerfilCliente().stream().map(p -> p.getTipo()).collect(Collectors.toSet()), cliente.getEnderecos().stream().map(ListarEnderecoDTO::new).toList(), cliente.getTelefones(), cliente.getImagemUrl());
    }
}
