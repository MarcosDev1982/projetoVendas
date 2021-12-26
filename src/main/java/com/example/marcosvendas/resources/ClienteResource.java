package com.example.marcosvendas.resources;

import com.example.marcosvendas.domain.Cliente;
import com.example.marcosvendas.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Cliente> findAll() {

        List<Cliente> clienteList = new ArrayList<>();
        return clienteList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id) {

        Cliente cliente = clienteService.finById(id);
        return ResponseEntity.ok().body(cliente);
    }


}
