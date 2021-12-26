package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.domain.Cliente;
import com.example.marcosvendas.repository.ClienteRepositories;
import com.example.marcosvendas.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepositories clienteRepositories;

    public Cliente finById(Integer id) {
        Optional<Cliente> obj = clienteRepositories.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado! Id" + id + ", Tipo: " + Categoria.class.getName()));
    }

}
