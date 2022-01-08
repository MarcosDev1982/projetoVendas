package com.example.marcosvendas.resources;

import com.example.marcosvendas.domain.Cliente;
import com.example.marcosvendas.dto.ClienteDTO;
import com.example.marcosvendas.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> clienteList = clienteService.findAll();
        List<ClienteDTO> clienteDTO = clienteList.stream().
                map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(clienteDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id) {

        Cliente cliente = clienteService.finById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente obj = clienteService.fromDTO(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(" / {id} ").buildAndExpand(obj.getId()).toUri();
        Cliente cliente = clienteService.insert(obj);

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/id", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente obj = clienteService.fromDTO(clienteDTO);
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> page(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "linesPage", defaultValue = "24") Integer lisnesPage,
                                                 @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                 @RequestParam(value = "decretion", defaultValue = "ASC") String decretion) {
        Page<Cliente> clientePage = clienteService.findPage(page, lisnesPage, orderBy, decretion);
        Page<ClienteDTO> clienteDTOS = clientePage.
                map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(clienteDTOS);


    }


}
