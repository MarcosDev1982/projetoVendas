package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepositories extends JpaRepository<Pagamento, Integer> {
}
