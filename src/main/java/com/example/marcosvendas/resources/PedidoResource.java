package com.example.marcosvendas.resources;

import com.example.marcosvendas.domain.Pedido;
import com.example.marcosvendas.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Pedido> findAll() {

        List<Pedido> pedidos = new ArrayList<>();
        return pedidos;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id) {

        Pedido pedido = pedidoService.finById(id);
        return ResponseEntity.ok().body(pedido);
    }


}