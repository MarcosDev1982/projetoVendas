package com.example.marcosvendas.resources.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErro extends StandardErro {


    List<FieldMessage> erros = new ArrayList<>();

    public ValidationErro(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public void addErro(String fieldNome, String fieldErro) {
        erros.add(new FieldMessage(fieldNome, fieldErro));
    }

}
