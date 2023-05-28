package com.rodrigo.ProdManager.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigo.ProdManager.domain.PagamentoBoleto;
import com.rodrigo.ProdManager.domain.PagamentoCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoCartao.class);
                objectMapper.registerSubtypes(PagamentoBoleto.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}
