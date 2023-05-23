package com.rodrigo.ProdManager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> items = new HashSet<>();
    public Pedido(Integer id, Date instante, Endereco enderecoEntrega, Cliente cliente) {
        this.id = id;
        this.instante = instante;
        this.enderecoEntrega = enderecoEntrega;
        this.cliente = cliente;
    }
}
