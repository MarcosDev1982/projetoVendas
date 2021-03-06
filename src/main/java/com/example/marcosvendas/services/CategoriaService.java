package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.dto.CategoriaDTO;
import com.example.marcosvendas.repository.CategoriaRepositories;
import com.example.marcosvendas.services.exception.DataIntegrityException;
import com.example.marcosvendas.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepositories categoriaRepositories;

    public List<Categoria> findAll() {
        return categoriaRepositories.findAll();
    }


    public Categoria finById(Integer id) {
        Optional<Categoria> obj = categoriaRepositories.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado! Id" + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria cat) {
        cat.setId(null);
        return categoriaRepositories.save(cat);
    }

    public Categoria update(Categoria cat) {
        Categoria newCategoria = finById(cat.getId());
        updateNew(newCategoria, cat);
        return categoriaRepositories.save(newCategoria);
    }

    public void delete(Integer id) {
        finById(id);

        try {
            categoriaRepositories.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é  possível excluir uma categoria que ja tem produtos");
        }
    }

    public Page<Categoria> findPage(Integer page, Integer lisnesPage, String orderBy, String decretion) {
        PageRequest pageRequest = PageRequest.of(page, lisnesPage, Sort.Direction.valueOf(decretion), orderBy);
        return categoriaRepositories.findAll(pageRequest);
    }

    public Categoria categoriaFromDTO(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());

    }

    private void updateNew(Categoria newCategoria, Categoria obj) {
        newCategoria.setNome(obj.getNome());

    }
}
