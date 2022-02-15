package com.example.marcosvendas.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("simulando envio de email");
        LOG.info(msg.toString());
        LOG.info("email enviado");


    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("simulando envio de email Html");
        LOG.info(msg.toString());
        LOG.info("email enviado");
    }

}
