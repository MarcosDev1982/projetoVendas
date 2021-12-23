package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositories extends JpaRepository<Categoria,Integer> {
}
