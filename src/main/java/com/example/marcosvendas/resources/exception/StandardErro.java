package com.example.marcosvendas.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardErro implements Serializable {

    private static final long serialVersionUID = 1;

    private  Integer status;

    private String msg;

    private  Long timeStamp;

}
