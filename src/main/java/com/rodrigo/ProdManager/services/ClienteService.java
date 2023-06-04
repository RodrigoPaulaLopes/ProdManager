package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.domain.Endereco;
import com.rodrigo.ProdManager.dtos.*;
import com.rodrigo.ProdManager.enums.PerfilCliente;
import com.rodrigo.ProdManager.repository.CidadeRepository;
import com.rodrigo.ProdManager.repository.ClienteRepository;
import com.rodrigo.ProdManager.repository.EnderecoRepository;
import com.rodrigo.ProdManager.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;


@Service
public class ClienteService implements UserDetailsService {

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

    @Autowired
    private UserAuthenticatedService userAuthenticatedService;

    public Page<ListarClientesDTO> findAll(Pageable paginacao){
        var hasRole = userAuthenticatedService.hasRoleAdmin();
        var cliente = userAuthenticatedService.clientAutenticado();
        return hasRole ? clienteRepository.findAll(paginacao).map(ListarClientesDTO::new) : clienteRepository.findById(paginacao, cliente.getId()).map(ListarClientesDTO::new);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.clienteRepository.findByEmail(email);
    }

    public Cliente clientAutenticado(){
        return (Cliente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public Boolean hasRoleAdmin(){
        var cliente = this.clientAutenticado();
        Set<PerfilCliente> perfis =  cliente.getPerfilCliente();
        boolean hasRole = false;
        for(PerfilCliente perfil : perfis){
            if(perfil.getCod() == 1){
                hasRole = true;
            }
        }
        return hasRole;
    }
}
