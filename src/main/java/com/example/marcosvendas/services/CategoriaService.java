package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.repository.CategoriaRepositories;
import com.example.marcosvendas.services.exception.DataIntegrityException;
import com.example.marcosvendas.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepositories categoriaRepositories;

    public Categoria finById(Integer id) {
        Optional<Categoria> obj = categoriaRepositories.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado! Id" + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria cat) {
        cat.setId(null);
        return categoriaRepositories.save(cat);
    }

    public Categoria update(Categoria cat) {
        finById(cat.getId());
        return categoriaRepositories.save(cat);
    }

    public void delete(Integer id) {
        finById(id);

        try {
            categoriaRepositories.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é  possível excluir uma categoria que ja tem produtos");
        }
    }
}
