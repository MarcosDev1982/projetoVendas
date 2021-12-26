package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepositories extends JpaRepository<Estado, Integer> {
}
