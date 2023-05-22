package com.rodrigo.ProdManager.domain;

import com.rodrigo.ProdManager.enums.EstadoPagamento;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class PagamentoBoleto extends Pagamento implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estadoPagamento.getCod(), pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
