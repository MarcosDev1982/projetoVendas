package com.example.marcosvendas.services;

import com.example.marcosvendas.domain.*;
import com.example.marcosvendas.domain.enums.EstadoPagamento;
import com.example.marcosvendas.repository.ItemPedidoRepositories;
import com.example.marcosvendas.repository.PagamentoRepositories;
import com.example.marcosvendas.repository.PedidoRepositories;
import com.example.marcosvendas.security.UserSS;
import com.example.marcosvendas.services.exception.AuthorizarionException;
import com.example.marcosvendas.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepositories pedidoRepositories;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepositories pagamentoRepositories;

    @Autowired
    private ItemPedidoRepositories itemPedidoRepositories;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;


    public Pedido finById(Integer id) {
        Optional<Pedido> obj = pedidoRepositories.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("objeto não encontrado! Id" + id + ", Tipo: " + Categoria.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) throws MessagingException {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.finById(obj.getCliente().getId()));
        obj.getPagamento().setEstadoPagamteno(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pago = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoBoleto(pago, obj.getInstante());
        }

        obj = pedidoRepositories.save(obj);
        pagamentoRepositories.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()
        ) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.finById(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepositories.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }

    public Page<Pedido> findPage(Integer page, Integer lisnesPage, String orderBy, String decretion) {
        UserSS userSS = UserService.authenticaeed();
        if (userSS == null) {
            throw new AuthorizarionException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, lisnesPage, Sort.Direction.valueOf(decretion), orderBy);
        Cliente cliente = clienteService.finById(userSS.getId());
        return pedidoRepositories.findByCliente(cliente, pageRequest);
    }

}
