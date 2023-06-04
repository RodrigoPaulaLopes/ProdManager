package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.enums.PerfilCliente;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserAuthenticatedService {

    public Cliente clientAutenticado(){
        return (Cliente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public  Boolean hasRoleAdmin(){
        var cliente = this.clientAutenticado();
        Set<PerfilCliente> perfis =  cliente.getPerfilCliente();
        boolean hasRole = false;
        for(PerfilCliente perfil : perfis){
            if(perfil.getCod() == 1){
                hasRole = true;
            }
        }
        return hasRole;
    }
}
