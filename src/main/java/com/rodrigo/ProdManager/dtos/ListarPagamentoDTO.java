package com.rodrigo.ProdManager.dtos;

import com.rodrigo.ProdManager.domain.Pagamento;
import com.rodrigo.ProdManager.enums.EstadoPagamento;

public record ListarPagamentoDTO(Integer id, EstadoPagamento estadoPagamento) {

    public ListarPagamentoDTO(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getEstadoPagamento());
    }
}
