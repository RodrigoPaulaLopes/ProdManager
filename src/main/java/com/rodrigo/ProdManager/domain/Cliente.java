package com.rodrigo.ProdManager.domain;

import com.rodrigo.ProdManager.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;


    public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.setId(id);
        this.setNome(nome);
        this.setEmail(email);
        this.setCpfOuCnpj(cpfOuCnpj);
        this.setTipoCliente(tipoCliente);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpfOuCnpj;

    private Integer tipoCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONES")
    private Set<String> telefones = new HashSet<>();

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }
}
