package com.rodrigo.ProdManager.enums;

import lombok.Getter;


@Getter
public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer cod;
    private String tipo;
    private EstadoPagamento(Integer cod, String tipo){
        this.cod = cod;
        this.tipo = tipo;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(EstadoPagamento x : EstadoPagamento.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
