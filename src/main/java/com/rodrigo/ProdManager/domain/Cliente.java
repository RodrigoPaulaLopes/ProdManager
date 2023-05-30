package com.rodrigo.ProdManager.domain;

import com.rodrigo.ProdManager.dtos.AtualizarClienteDTO;
import com.rodrigo.ProdManager.dtos.InserirClienteDTO;
import com.rodrigo.ProdManager.enums.PerfilCliente;
import com.rodrigo.ProdManager.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;


    public Cliente(){
        this.addPerfilCliente(PerfilCliente.CLIENTE);
    }
    public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.setId(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setCpfOuCnpj(cpfOuCnpj);
        this.setTipoCliente(tipoCliente);
    }

    public Cliente(InserirClienteDTO dados) {
        this.setNome(dados.nome());
        this.setEmail(dados.email());
        this.setCpfOuCnpj(dados.cpfOuCnpj());
        this.setSenha(dados.senha());
        this.setTipoCliente(TipoCliente.toEnum(dados.tipo()));
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfilCliente = new HashSet<>();


    private Integer tipoCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONES")
    private Set<String> telefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

    public void addPerfilCliente(PerfilCliente perfilCliente) {
        this.perfilCliente.add(perfilCliente.getCod());
    }

    public Set<PerfilCliente> getPerfilCliente() {
        return perfilCliente.stream().map(p -> PerfilCliente.toEnum(p)).collect(Collectors.toSet());
    }

    public void atualizar(AtualizarClienteDTO clienteDTO) {
        this.setId(clienteDTO.id());
        this.setNome(clienteDTO.nome());
        this.setEmail(clienteDTO.email());
        this.setCpfOuCnpj(clienteDTO.cpfOuCnpj());
        this.setSenha(clienteDTO.senha());
        this.setTipoCliente(TipoCliente.toEnum(clienteDTO.tipo()));
    }


}
