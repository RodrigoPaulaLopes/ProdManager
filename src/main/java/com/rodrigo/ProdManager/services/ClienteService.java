package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Categoria;
import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.domain.Endereco;
import com.rodrigo.ProdManager.dtos.*;
import com.rodrigo.ProdManager.enums.TipoCliente;
import com.rodrigo.ProdManager.repository.CidadeRepository;
import com.rodrigo.ProdManager.repository.ClienteRepository;
import com.rodrigo.ProdManager.repository.EnderecoRepository;
import com.rodrigo.ProdManager.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<ListarClientesDTO> findAll(Pageable paginacao){
        return clienteRepository.findAll(paginacao).map(ListarClientesDTO::new);
    }

    public ListarClientesDTO findById(Long id){
        return new ListarClientesDTO(clienteRepository.getReferenceById(id));
    }

    public ListarClientesDTO update(AtualizarClienteDTO dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        var novaSenha = passwordEncoder.encode(dados.senha());
        cliente.setSenha(novaSenha);
        cliente.atualizar(dados);
        return new ListarClientesDTO(clienteRepository.save(cliente));
    }
    public ListarClientesDTO create(InserirClienteDTO dados) {
        var cidade = cidadeRepository.getReferenceById(dados.cidadeId());
        var estado = estadoRepository.getReferenceById(dados.estadoId());

        estado.setCidades(Arrays.asList(cidade));
        cidade.setEstado(estado);


        Cliente cli = new Cliente(dados);
        var novaSenha = passwordEncoder.encode(dados.senha());
        cli.setSenha(novaSenha);
        Endereco end = new Endereco(dados, cidade, cli);

        cli.getEnderecos().add(end);
        cli.getTelefones().addAll(Arrays.asList(dados.telefone1(), dados.telefone2()));

        enderecoRepository.saveAll(cli.getEnderecos());
        return new ListarClientesDTO(clienteRepository.save(cli));
    }

    public void delete(long id) {
        var cliente = clienteRepository.getReferenceById(id);
        clienteRepository.delete(cliente);
    }
}
