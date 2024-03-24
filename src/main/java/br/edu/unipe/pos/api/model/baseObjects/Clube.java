package br.edu.unipe.pos.api.model.baseObjects;

import br.edu.unipe.pos.api.validation.EmailValidation;
import br.edu.unipe.pos.api.validation.NomeClubeValidation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Clube implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O nome do clube deve ser informado.")
    @NomeClubeValidation
    private String nome;

    @NotEmpty(message = "O estado do clube deve ser informado.")
    private String estado;

    @Email
    @EmailValidation
    private String email;

    @NotEmpty(message = "O Cnpj do clube deve ser informado.")
    private String cnpj;

    @NotNull(message = "A data não pode ser nula")
    private Date dataCriacao;
}
