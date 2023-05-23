package com.rodrigo.ProdManager.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class ItemPedido implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();
    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido(Produto produto, Pedido pedido, Double desconto, Integer quantidade, Double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public Pedido getPedido() {
        return id.getPedido();
    }

    public ItemPedidoPk getId() {
        return id;
    }

    public void setId(ItemPedidoPk id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
