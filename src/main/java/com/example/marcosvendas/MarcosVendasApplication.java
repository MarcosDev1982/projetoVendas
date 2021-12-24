package com.example.marcosvendas;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.domain.Cidade;
import com.example.marcosvendas.domain.Estado;
import com.example.marcosvendas.domain.Produto;
import com.example.marcosvendas.repository.CategoriaRepositories;
import com.example.marcosvendas.repository.CidadeRepositories;
import com.example.marcosvendas.repository.EstadoRepositories;
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

    @Autowired
    CidadeRepositories cidadeRepositories;

    @Autowired
    EstadoRepositories estadoRepositories;

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


        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2,c3));



        categoriaRepositories.saveAll(Arrays.asList(cat1, cat2));

        produtoRepositories.saveAll(Arrays.asList(p1, p2, p3));

        estadoRepositories.saveAll(Arrays.asList(est1, est2));

        cidadeRepositories.saveAll(Arrays.asList(c1,c2,c3));
    }
}
