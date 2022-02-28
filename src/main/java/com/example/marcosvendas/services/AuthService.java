package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Cliente;
import com.example.marcosvendas.repository.ClienteRepositories;
import com.example.marcosvendas.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private Random random = new Random();

    @Autowired
    private ClienteRepositories clienteRepositories;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;


    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepositories.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }
        String newPass = newPasswoed();
        cliente.setSenha(bCryptPasswordEncoder.encode(newPass));

        clienteRepositories.save(cliente);
        emailService.sendNewPassword(cliente, newPass);
    }

    private String newPasswoed() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0) {
            return (char) (random.nextInt(10) + 48);
        } else if (opt == 1) {
            return (char) (random.nextInt(26) + 65);

        } else {
            return (char) (random.nextInt(10) + 97);

        }
    }

}
