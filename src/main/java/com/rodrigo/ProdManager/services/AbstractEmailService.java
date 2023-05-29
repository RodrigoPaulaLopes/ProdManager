package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Date;


public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String from;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessage(obj);
        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessage(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();

        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(from);
        sm.setSubject("Pedido confirmado: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }
}
