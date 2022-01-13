package com.example.marcosvendas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;


@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    @EqualsAndHashCode.Include()
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;

    private Double preco;

    private Integer quantidade;

    public ItemPedido() {

    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }



    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder builder = new StringBuilder();
        builder.append(getProduto().getNome());
        builder.append(", Qte: ");
        builder.append(getQuantidade());
        builder.append(", Preço unitário: ");
        builder.append(nf.format(getPreco()));
        builder.append(", Subtotal: ");
        builder.append("\n");
        return builder.toString();
    }
}
