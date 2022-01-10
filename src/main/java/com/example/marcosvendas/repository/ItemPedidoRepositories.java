package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepositories extends JpaRepository<ItemPedido, Integer> {
}
