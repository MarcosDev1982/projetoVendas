package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositories extends JpaRepository<Cliente, Integer> {
}
