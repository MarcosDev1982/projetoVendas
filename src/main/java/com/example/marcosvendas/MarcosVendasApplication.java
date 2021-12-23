package com.example.marcosvendas;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.domain.Produto;
import com.example.marcosvendas.repository.CategoriaRepositories;
import com.example.marcosvendas.repository.ProdutoRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MarcosVendasApplication implements CommandLineRunner {

    @Autowired
    CategoriaRepositories categoriaRepositories;

    @Autowired
    ProdutoRepositories produtoRepositories;

    public static void main(String[] args) {
        SpringApplication.run(MarcosVendasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(1, "Informatica");
        Categoria cat2 = new Categoria(2, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));


        categoriaRepositories.saveAll(Arrays.asList(cat1, cat2));

        produtoRepositories.saveAll(Arrays.asList(p1, p2, p3));
    }
}
