package br.edu.unipe.pos.api.controller;

import br.edu.unipe.pos.api.client.ApiFootballClient;
import br.edu.unipe.pos.api.model.baseObjects.Clube;
import br.edu.unipe.pos.api.model.dto.*;
import br.edu.unipe.pos.api.model.dto.footballDto.TeamInfo;
import br.edu.unipe.pos.api.model.dto.footballDto.TeamResponseDto;
import br.edu.unipe.pos.api.service.ClubeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/football")
public class FootballController {

    private ClubeService clubeService;
    private ApiFootballClient footballClient;
    static final String API_KEY = "896a460e4fmshc91e68e54f7c68cp1e3981jsna1a339c480dc";
    static final String API_HOST = "api-football-v1.p.rapidapi.com";

    @GetMapping("/football")
    @Operation(summary  = "Listar clubes brasileiros de futebol na API Football.",
            description = "Retorna a lista de clubes de futebol disponíveis no Brasil consultando a API de Football.")
    public ResponseEntity<TeamResponseDto> listarClubesFootball()
    {
        return ResponseEntity.ok(footballClient.consultClubs(API_KEY, API_HOST));
    }
    @GetMapping("/football/{nome}")
    @Operation(summary  = "Consultar clube de futebol pelo nome na API Football.",
            description = "Retorna se encontrar os detalhes do clube consultado a API de Football.")
    public ResponseEntity<TeamResponseDto> buscarClubeFootball(@PathVariable String nome)
    {
        return ResponseEntity.ok(footballClient.consultClub(nome, API_KEY, API_HOST));
    }

    @PostMapping("/football/cadastro")
    @Operation(summary = "Cadastrar clube com dados da Api de Football",
            description = "Busca um clube pelo nome informado e se encontrar cadastra um novo clube com os dados encontrados.")
    public ResponseEntity<?>cadastroClubeByFootball(ClubeByFootbalApiDto clubeNome)
    {
        TeamResponseDto consultaFootball = footballClient.consultClub(clubeNome.getNome(), API_KEY, API_HOST);
        if(consultaFootball.results == 0)
        {
            ErroDto errorResponse = ErroDto.builder()
                    .error("Erro ao consultar clube na API")
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Não foi possível cadastrar, Clube " + clubeNome.getNome() + " não encontrado na API de futebol.")
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        else
        {
            TeamInfo teamInfo = consultaFootball.response.get(0);
            Clube newClube = clubeService.createClubeByFootball(teamInfo);
            newClube = clubeService.salvarClube(newClube);
            return ResponseEntity.ok(newClube);
        }
    }
}
