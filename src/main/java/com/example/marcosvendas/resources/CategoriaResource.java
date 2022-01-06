package com.example.marcosvendas.resources;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.dto.CategoriaDTO;
import com.example.marcosvendas.repository.CategoriaRepositories;
import com.example.marcosvendas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaRepositories categoriaRepositories;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> categoriaList = categoriaService.findAll();
        List<CategoriaDTO> categoriaDTOS = categoriaList.stream().
                map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());


        return ResponseEntity.ok().body(categoriaDTOS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Categoria categoriaOptional = categoriaService.finById(id);
        return ResponseEntity.ok().body(categoriaOptional);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Categoria obj) {
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> update(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> page(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "linesPage", defaultValue = "24") Integer lisnesPage,
                                                   @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                   @RequestParam(value = "decretion", defaultValue = "ASC") String decretion) {
        Page<Categoria> categoriaList = categoriaService.findPage(page, lisnesPage, orderBy, decretion);
        Page<CategoriaDTO> categoriaDTOS = categoriaList.
                map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(categoriaDTOS);


    }


}
