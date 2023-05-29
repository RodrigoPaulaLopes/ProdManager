package com.rodrigo.ProdManager.domain;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "cidades")
public class Cidade implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    public Cidade(Integer id, String nome, Estado estado){
        this.setId(id);
        this.setNome(nome);
        this.setEstado(estado);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @OneToMany(mappedBy = "cidade")
    private List<Endereco> enderecos = new ArrayList<>();





}
