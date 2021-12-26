package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositories extends JpaRepository<Endereco, Integer> {
}
