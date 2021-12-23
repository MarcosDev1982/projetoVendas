package com.example.marcosvendas;

import com.example.marcosvendas.domain.Categoria;
import com.example.marcosvendas.repository.CategoriaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MarcosVendasApplication implements CommandLineRunner {

    @Autowired
    CategoriaRepositories categoriaRepositories;

    public static void main(String[] args) {
        SpringApplication.run(MarcosVendasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat = new Categoria(1, "Informatica");
        Categoria cat2 = new Categoria(2, "Escritório");

        categoriaRepositories.saveAll(Arrays.asList(cat, cat2));


    }
}
