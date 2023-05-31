package com.rodrigo.ProdManager.resources;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.dtos.CredenciaisDTO;
import com.rodrigo.ProdManager.dtos.ListarTokenDTO;
import com.rodrigo.ProdManager.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<ListarTokenDTO> login(@RequestBody CredenciaisDTO dados){
        var autentication = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var subject = this.authenticationManager.authenticate(autentication);

        var token = tokenService.gerarToken((Cliente) subject.getPrincipal());

        return ResponseEntity.ok().body(new ListarTokenDTO(token));
    }
}
