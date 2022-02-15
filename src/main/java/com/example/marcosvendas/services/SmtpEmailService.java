package com.example.marcosvendas.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
    @Autowired
    private MailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("simulando envio de email");
        mailSender.send(msg);
        LOG.info("email enviado");


    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("simulando envio de email HTML");
        javaMailSender.send(msg);
        LOG.info("email enviado");
    }
}

