package com.example.marcosvendas.domain;

import com.example.marcosvendas.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartão extends Pagamento {

    private static final long serialVersionUID = 1L;

    private Integer numeroParcelas;

    public PagamentoComCartão() {

    }

    public PagamentoComCartão(Integer id, EstadoPagamento estadoPagamteno, Pedido pedido, Integer numeroParcelas) {
        super(id, estadoPagamteno, pedido);
        this.numeroParcelas = numeroParcelas;
    }


}
