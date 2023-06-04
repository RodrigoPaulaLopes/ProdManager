package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.enums.PerfilCliente;
import com.rodrigo.ProdManager.repository.ClienteRepository;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
@Service
public class UserAuthenticatedService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder encoder;

    private Random random;
    @Autowired
    private EmailService emailService;
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

    public void sendNewPassword(String email) throws MessagingException {
        Cliente cliente = clienteRepository.buscarPeloEmail(email);

        if(cliente == null){
            throw new EntityNotFoundException();
        }

        String newPass = "1234";
        cliente.setSenha(encoder.encode(newPass));

        clienteRepository.save(cliente);

        emailService.sendNewPassword(cliente, newPass);
    }

    private String newPassword() {

        char[] vet = new char[10];

        for (int i =0; i < vet.length; i++){
            vet[i] = randomChar();
        }

        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt();
        if(opt == 0){
            return (char) (random.nextInt(10) + 48);
        }else if(opt == 1){
            return (char) (random.nextInt(26) + 65);
        }else{
            return (char) (random.nextInt(26) + 97);
        }
    }


}
