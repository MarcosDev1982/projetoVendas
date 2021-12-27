package com.example.marcosvendas.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public Cidade() {

    }

    public Cidade(Integer id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
}
