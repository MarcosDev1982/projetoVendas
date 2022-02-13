package com.example.marcosvendas.domain;


import com.example.marcosvendas.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include()
    private Integer id;

    private Integer estadoPagamteno;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento() {

    }

    public Pagamento(Integer id, EstadoPagamento estadoPagamteno, Pedido pedido) {
        this.id = id;
        this.estadoPagamteno = (estadoPagamteno == null) ? null : estadoPagamteno.getCod();
        this.pedido = pedido;
    }


    public EstadoPagamento getEstadoPagamteno() {
        return EstadoPagamento.toEnum(estadoPagamteno);
    }

    public void setEstadoPagamteno(EstadoPagamento estadoPagamteno) {
        this.estadoPagamteno = estadoPagamteno.getCod();
    }

}


