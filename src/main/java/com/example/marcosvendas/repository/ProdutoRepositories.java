package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositories extends JpaRepository<Produto, Integer> {
}
