package com.example.marcosvendas.dto;

import com.example.marcosvendas.domain.Categoria;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho tem que ser entre 5 e 80 caracteres ")
    private String nome;

    public CategoriaDTO() {

    }

    public CategoriaDTO(Categoria obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

    public CategoriaDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
