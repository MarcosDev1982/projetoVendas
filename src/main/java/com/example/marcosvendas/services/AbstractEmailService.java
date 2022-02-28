package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Cliente;
import com.example.marcosvendas.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;


public abstract class AbstractEmailService implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Value("default.sender")
    private String sender;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void senOrderCofirmationEmail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Codigo: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;

    }

    protected String htmlFromTemplatePedido(Pedido obj) {
        Context context = new Context();
        context.setVariable("pedido", obj);
        return templateEngine.process("email/confirmacaoPedido", context);
    }


    public void sendOrderConfirmationHtmlEmail(Pedido obj) throws MessagingException {
        MimeMessage sm = prepareMimeMessageFromPedido(obj);
        sendHtmlEmail(sm);
    }

    protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(obj.getCliente().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Pedido Confirmado codgio" + obj.getId());
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplatePedido(obj), true);
        return mimeMessage;
    }

    @Override
    public void sendNewPassword(Cliente cliente, String newPass) {
        SimpleMailMessage sm = prepareNewPassawordMail(cliente, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPassawordMail(Cliente cliente, String newPass) {

        SimpleMailMessage sm = new SimpleMailMessage();

        sm.setTo(cliente.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Soliciatação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("nova senha " + newPass);
        return sm;

    }


}
