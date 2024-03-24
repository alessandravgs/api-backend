package br.edu.unipe.pos.api.model.dto;

import br.edu.unipe.pos.api.model.baseObjects.Clube;
import lombok.Data;

import java.util.Date;

@Data
public class ClubeResponseDto {

    private Integer id;
    private String nome;
    private String estado;
    private String email;
    private String cnpj;
    private Date dataCriacao;

    public ClubeResponseDto(Clube clube) {
        this.id = clube.getId();
        this.nome = clube.getNome();
        this.estado = clube.getEstado();
        this.email = clube.getEmail();
        this.cnpj = clube.getCnpj();
        this.dataCriacao = clube.getDataCriacao();
    }
}
