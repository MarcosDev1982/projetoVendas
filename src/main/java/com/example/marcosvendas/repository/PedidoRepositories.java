package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositories extends JpaRepository<Pedido, Integer> {
}
