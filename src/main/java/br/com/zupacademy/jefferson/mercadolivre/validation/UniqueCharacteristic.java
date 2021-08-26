package br.com.zupacademy.jefferson.mercadolivre.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueCharacteristicValidator.class})
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCharacteristic {

    String message() default "Não pode haver nomes de características iguais.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
