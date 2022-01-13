package com.example.marcosvendas.repository;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepositories extends JpaRepository<Produto, Integer> {

 @Query("SELECT DISTINCT obj from Produto obj INNER JOIN obj.categorias cat where obj.nome LIKE %:nome% AND cat IN :categorias")
 Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categoriasList, Pageable pageRequest);


}
