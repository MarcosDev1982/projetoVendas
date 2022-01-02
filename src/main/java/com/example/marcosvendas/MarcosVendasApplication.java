package com.example.marcosvendas;

import com.example.marcosvendas.domain.*;
import com.example.marcosvendas.domain.enums.EstadoPagamento;
import com.example.marcosvendas.domain.enums.TipoCliente;
import com.example.marcosvendas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

    @Autowired
    private PedidoRepositories pedidoRepositories;

    @Autowired
    PagamentoRepositories pagamentoRepositories;

    @Autowired
    ItemPedidoRepositories itemPedidoRepositories;


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

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepositories.saveAll(Arrays.asList(est1, est2));
        cidadeRepositories.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "55373553026", TipoCliente.PESSOAFISICA);
        cl1.getTelefones().addAll(Arrays.asList("23736200", "40202000"));

        Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 3003", "Jardin", "8220834", cl1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Saida 800", "centro", "38777012", cl1, c2);

        cl1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepositories.saveAll(Arrays.asList(cl1));

        enderecoRepositories.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:32"), cl1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2019 19:35"), cl1, e2);


        Pagamento pagto1 = new PagamentoComCartão(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepositories.saveAll(Arrays.asList(ped1, ped2));

        pagamentoRepositories.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));


        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepositories.saveAll(Arrays.asList(ip1, ip2, ip3));


    }
}
