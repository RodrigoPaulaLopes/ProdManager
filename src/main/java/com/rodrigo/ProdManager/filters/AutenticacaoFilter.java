package com.rodrigo.ProdManager.filters;

import com.rodrigo.ProdManager.repository.ClienteRepository;
import com.rodrigo.ProdManager.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpRequest;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getToken(request);

        if(token != null){
            var subject = tokenService.getSubject(token);
            var cliente = clienteRepository.findByEmail(subject);
            var autenticado = new UsernamePasswordAuthenticationToken(cliente,null, cliente.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticado);
        }

        filterChain.doFilter(request, response);
    }

    public String getToken(HttpServletRequest request){
        var token = request.getHeader("Authorization");

        if (token != null){
            return token.replace("Bearer ", "");
        }
        return null;
    }
}
