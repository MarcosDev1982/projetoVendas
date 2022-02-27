package com.example.marcosvendas.resources;

import com.example.marcosvendas.domain.Pedido;
import com.example.marcosvendas.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

   /* @RequestMapping(method = RequestMethod.GET)
    public List<Pedido> findAll() {

        List<Pedido> pedidos = new ArrayList<>();
        return pedidos;

    }*/

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> findById(@PathVariable Integer id) {

        Pedido pedido = pedidoService.finById(id);
        return ResponseEntity.ok().body(pedido);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) throws MessagingException {
        obj = pedidoService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "linesPage", defaultValue = "24") Integer lisnesPage,
                                                 @RequestParam(value = "orderBy", defaultValue = "instante") String orderBy,
                                                 @RequestParam(value = "decretion", defaultValue = "DESC") String decretion) {
        Page<Pedido> pedidosList = pedidoService.findPage(page, lisnesPage, orderBy, decretion);
        return ResponseEntity.ok().body(pedidosList);


    }


}