package com.example.marcosvendas;

import com.example.marcosvendas.domain.*;
import com.example.marcosvendas.domain.enums.TipoCliente;
import com.example.marcosvendas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MarcosVendasApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepositories categoriaRepositories;

    @Autowired
    private ProdutoRepositories produtoRepositories;

    @Autowired
    private CidadeRepositories cidadeRepositories;

    @Autowired
    private EstadoRepositories estadoRepositories;

    @Autowired
    private ClienteRepositories clienteRepositories;

    @Autowired
    private EnderecoRepositories enderecoRepositories;

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
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "55373553026", TipoCliente.PESSOAFISICA);
        cl1.getTelefones().addAll(Arrays.asList("23736200", "40202000"));

        Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 3003", "Jardin", "8220834", cl1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Saida 800", "centro", "38777012", cl1, c2);

        cl1.getEnderecos().addAll(Arrays.asList(e1, e2));




        categoriaRepositories.saveAll(Arrays.asList(cat1, cat2));

        produtoRepositories.saveAll(Arrays.asList(p1, p2, p3));

        estadoRepositories.saveAll(Arrays.asList(est1, est2));

        clienteRepositories.saveAll(Arrays.asList(cl1));

        enderecoRepositories.saveAll(Arrays.asList(e1, e2));


    }
}
