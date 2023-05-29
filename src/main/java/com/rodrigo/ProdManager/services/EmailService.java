package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
}
