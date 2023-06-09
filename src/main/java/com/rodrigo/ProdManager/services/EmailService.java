package com.rodrigo.ProdManager.services;

import com.rodrigo.ProdManager.domain.Cliente;
import com.rodrigo.ProdManager.domain.Pedido;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);
    void sendHtmlEmail(MimeMessage msg);

    void sendNewPassword(Cliente cliente, String newPass) throws MessagingException;
}
