package com.example.marcosvendas.resources.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fildName;
    private String messag;

    public FieldMessage() {

    }

    public FieldMessage(String fildName, String messag) {
        this.fildName = fildName;
        this.messag = messag;
    }
}
