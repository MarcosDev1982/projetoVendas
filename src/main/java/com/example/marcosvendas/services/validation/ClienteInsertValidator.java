package com.example.marcosvendas.services.validation;

import com.example.marcosvendas.domain.enums.TipoCliente;
import com.example.marcosvendas.dto.ClienteNewDTO;
import com.example.marcosvendas.resources.exception.FieldMessage;
import com.example.marcosvendas.services.validation.util.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodig()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfoucnpj", "Cpf inválido"));
        }
        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodig()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfoucnpj", "Cnpj inválido"));
        }


        // inclua os testes aqui, inserindo erros na lista

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessag())
                    .addPropertyNode(e.getFildName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}


