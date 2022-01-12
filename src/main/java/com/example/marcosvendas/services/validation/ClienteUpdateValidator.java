package com.example.marcosvendas.services.validation;

import com.example.marcosvendas.domain.Cliente;
import com.example.marcosvendas.dto.ClienteDTO;
import com.example.marcosvendas.repository.ClienteRepositories;
import com.example.marcosvendas.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ClienteRepositories clienteRepositories;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));
        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepositories.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getEnderecos().equals(uriId)) {
            list.add(new FieldMessage("Email", "Email já existente"));
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


