package com.example.marcosvendas.resources;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    CategoriaService categoriaService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> findAll() {


        List<Categoria> categoriaList = new ArrayList<>();
        return categoriaList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Categoria categoriaOptional = categoriaService.finById(id);
        return ResponseEntity.ok().body(categoriaOptional);
    }


}
