package com.rodrigo.ProdManager.configuration;

import com.rodrigo.ProdManager.services.DatabaseService;
import com.rodrigo.ProdManager.services.EmailService;
import com.rodrigo.ProdManager.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    DatabaseService databaseService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiate() throws ParseException {

        if(!"create".equals(strategy)){
            return false;
        }
        databaseService.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
