package br.edu.unipe.pos.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NomeClubeValidator.class)
public @interface NomeClubeValidation {
    String message() default "Não permitido cadastrar clube com este nome: ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
