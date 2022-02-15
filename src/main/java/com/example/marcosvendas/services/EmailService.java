package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface EmailService {

    void senOrderCofirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj) throws MessagingException;

    void sendHtmlEmail(MimeMessage msg);


}
