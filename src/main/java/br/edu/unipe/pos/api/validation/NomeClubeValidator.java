package br.edu.unipe.pos.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class NomeClubeValidator implements
        ConstraintValidator<NomeClubeValidation, String>{
    private String message;

    @Override
    public void initialize(NomeClubeValidation constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String nomeClube, ConstraintValidatorContext constraintValidatorContext) {
        List<String> clubesProibidos =
                Arrays.asList("Flamengo", "Palmeiras", "Fluminense", "Botafogo");

        if(!clubesProibidos.contains(nomeClube)) {
            return true;
        }

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(message + nomeClube)
                .addConstraintViolation();
        return false;
    }
}
