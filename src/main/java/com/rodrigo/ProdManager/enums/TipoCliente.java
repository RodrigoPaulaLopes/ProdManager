package com.rodrigo.ProdManager.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Fisica"),
    PESSSOAJURIDICA(2, "Pessoa Juridica");

    private Integer cod;
    private String tipo;
    private TipoCliente(Integer cod, String tipo){
        this.cod = cod;
        this.tipo = tipo;
    }

    public static TipoCliente toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(TipoCliente x : TipoCliente.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
