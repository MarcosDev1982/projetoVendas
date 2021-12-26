package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepositories extends JpaRepository<Cidade, Integer> {
}
