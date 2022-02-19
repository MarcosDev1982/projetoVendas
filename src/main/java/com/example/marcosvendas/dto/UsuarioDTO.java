package com.example.marcosvendas.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String senha;

    public UsuarioDTO() {

    }

    public UsuarioDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}
