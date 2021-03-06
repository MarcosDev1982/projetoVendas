package com.example.marcosvendas.config;

import com.example.marcosvendas.services.DBService;
import com.example.marcosvendas.services.EmailService;
import com.example.marcosvendas.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DBService serviceDB;

    @Bean
    public boolean istantiateDataBase() throws ParseException {

        serviceDB.istantiateDataBase();

        return true;
    }

  /*  @Bean
    public EmailService emailService() {
        return new MockEmailService();
    } */

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }


}
