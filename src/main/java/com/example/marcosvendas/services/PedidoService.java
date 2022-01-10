package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.domain.Pedido;
import com.example.marcosvendas.repository.PedidoRepositories;
import com.example.marcosvendas.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepositories pedidoRepositories;

    public Pedido finById(Integer id) {
        Optional<Pedido> obj = pedidoRepositories.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado! Id" + id + ", Tipo: " + Categoria.class.getName()));
    }

}
