package com.rodrigo.ProdManager.configuration;

import com.rodrigo.ProdManager.services.DatabaseService;
import com.rodrigo.ProdManager.services.EmailService;
import com.rodrigo.ProdManager.services.MockMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    DatabaseService databaseService;

    @Bean
    public boolean instantiate() throws ParseException {

        databaseService.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockMailService();
    }
}
