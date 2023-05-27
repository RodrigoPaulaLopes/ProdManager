package com.rodrigo.ProdManager.services.validations;

import java.util.ArrayList;
import java.util.List;

import com.rodrigo.ProdManager.dtos.InserirClienteDTO;
import com.rodrigo.ProdManager.exceptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClientValidator implements ConstraintValidator<ClientInsert, InserirClienteDTO> {
    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(InserirClienteDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();


        //preciso voltar aqui depois.
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
