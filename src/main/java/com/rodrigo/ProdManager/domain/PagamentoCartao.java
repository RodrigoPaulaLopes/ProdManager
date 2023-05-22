package com.rodrigo.ProdManager.domain;

import com.rodrigo.ProdManager.enums.EstadoPagamento;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PagamentoCartao extends Pagamento implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    private Integer numParcelas;

    public PagamentoCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numParcelas) {
        super(id, estadoPagamento.getCod(), pedido);
        this.numParcelas = numParcelas;
    }
}
