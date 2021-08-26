package br.com.zupacademy.jefferson.mercadolivre.validation;

import br.com.zupacademy.jefferson.mercadolivre.controller.data.request.ProdutoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCharacteristicValidator implements ConstraintValidator<UniqueCharacteristic, ProdutoRequest> {
    @Override
    public boolean isValid(ProdutoRequest produtoRequest, ConstraintValidatorContext context) {
        return !produtoRequest.compareCharacteristic();
    }
}
