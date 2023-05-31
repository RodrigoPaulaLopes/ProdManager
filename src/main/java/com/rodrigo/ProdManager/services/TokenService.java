package com.rodrigo.ProdManager.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rodrigo.ProdManager.domain.Cliente;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    private static final String SECRET = "SECRET";


    public String gerarToken(Cliente cliente){
        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            var token = JWT.create().withIssuer("ProdManager")
                    .withSubject(cliente.getEmail())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algoritmo);
            return token;
        }catch (JWTVerificationException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getSubject(String token) {
        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            var tokenSubject = JWT.require(algoritmo)
                    .withIssuer("ProdManager")
                    .build()
                    .verify(token)
                    .getSubject();
            return tokenSubject;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Erro ao verificar token " + e);
        }
    }

}
