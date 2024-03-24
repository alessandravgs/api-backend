package br.edu.unipe.pos.api.exception;

import br.edu.unipe.pos.api.model.dto.ErroDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;

@Order
@Log4j2
@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErroDto> handleValidationExceptions(Exception e) {

        ErroDto errorCapture =
                    ErroDto.builder()
                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .code(HttpStatus.BAD_REQUEST.value())
                            .message(e.getMessage())
                            .build();

        log.info("Erros:", errorCapture);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCapture);
    }


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public ResponseEntity<ErroDto> handlerException(NoSuchElementException e) {

        ErroDto erroCapturado =
                ErroDto.builder() //
                        .error(HttpStatus.NOT_FOUND.getReasonPhrase()) //
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .build();

        log.info("Erros", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroCapturado);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErroDto> handlerException(MethodArgumentNotValidException e) {

        List<String> constraintsViolated = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " - " + error.getDefaultMessage())
                .collect(Collectors.toList());

        constraintsViolated.addAll(
                e.getBindingResult().getGlobalErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList())
        );

        ErroDto erroCapturado = ErroDto.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(constraintsViolated.toString())
                .build();

        log.info("Erros", constraintsViolated);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroCapturado);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity <List<ErroDto>> handleValidationExceptions(jakarta.validation.ConstraintViolationException e) {
        List<ErroDto> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            ErroDto errorCapture = ErroDto.builder()
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(violation.getMessage())
                    .build();
            errors.add(errorCapture);
        }
        log.info("Erros:", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
