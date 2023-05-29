package com.rodrigo.ProdManager.services;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractEmailService{
    public static final Logger LOG = (Logger) LoggerFactory.getLogger(MockMailService.class);
    @Override
    public void sendEmail(SimpleMailMessage msg) {
    LOG.info("Simulando envio de email");
    LOG.info(msg.toString());
    LOG.info("Email enviado");
    }
}
