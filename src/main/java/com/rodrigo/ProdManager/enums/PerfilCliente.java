package com.rodrigo.ProdManager.enums;

import lombok.Getter;

@Getter
public enum PerfilCliente {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private Integer cod;
    private String tipo;
    private PerfilCliente(Integer cod, String tipo){
        this.cod = cod;
        this.tipo = tipo;
    }

    public static PerfilCliente toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(PerfilCliente x : PerfilCliente.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
