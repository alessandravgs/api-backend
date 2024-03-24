package br.edu.unipe.pos.api.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class ClubeDto {
    private String nome;
    private String estado;
    private String email;
    private String cnpj;
    private Date dataCriacao;
}
