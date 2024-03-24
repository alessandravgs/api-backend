package br.edu.unipe.pos.api.controller;

import br.edu.unipe.pos.api.model.baseObjects.Clube;
import br.edu.unipe.pos.api.model.dto.ClubeDto;
import br.edu.unipe.pos.api.model.dto.ClubeResponseDto;
import br.edu.unipe.pos.api.service.ClubeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/clube")
public class ClubeController {

    private ClubeService clubeService;

    @PostMapping
    @Operation(summary = "Cadastrar clube",
            description = "Cadastra um novo clube.")
    public ResponseEntity<Clube> cadastrarClube(@RequestBody ClubeDto clubeDto) {
        Clube objSave = clubeService.createClubeByDto(clubeDto);
        objSave = clubeService.salvarClube(objSave);
        return ResponseEntity.ok(objSave);
    }

    @PutMapping
    @Operation(summary = "Alterar clube",
            description = "Altera os dados de um clube existente.")
    public ResponseEntity<Clube> alterarClube(@RequestBody Clube clube) {
        clube = clubeService.alterarClube(clube);
        return ResponseEntity.ok(clube);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Excluir clube",
            description = "Exclui um clube do sistema com base no ID fornecido.")
    public ResponseEntity<Void> excluirClube(@PathVariable Integer id) {
        clubeService.excluirClube(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar clubes",
            description = "Lista todos os clubes cadastrados.")
    public ResponseEntity<List<ClubeResponseDto>> listarClubes() {
        List<Clube> clubes = clubeService.listarClubes();
        List<ClubeResponseDto> clubesResponseDTO = clubes.stream()
                .map(ClubeResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clubesResponseDTO);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar clube por ID",
            description = "Retorna um clube específico com base no ID fornecido.")
    public ResponseEntity<ClubeResponseDto> buscarClubeById(@PathVariable Integer id) {
        Clube clube = clubeService.consultarByID(id);
        if (clube == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClubeResponseDto(clube));
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar clube por nome",
            description = "Retorna uma lista de clubes que possuam o nome fornecido.")
    public ResponseEntity<List<ClubeResponseDto>> buscarClubeByNome(@PathVariable String nome) {
        List<Clube> clubes = clubeService.consultarByNome(nome);
        List<ClubeResponseDto> clubesResponseDTO = clubes.stream()
                .map(ClubeResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clubesResponseDTO);
    }

}
