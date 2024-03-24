package br.edu.unipe.pos.api.client;

import br.edu.unipe.pos.api.model.dto.footballDto.TeamResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value="football" , url = "https://api-football-v1.p.rapidapi.com/v3/")
public interface ApiFootballClient {
    @GetMapping("teams?country=Brazil&name={nome}")
    TeamResponseDto consultClub(@PathVariable("nome") String nome,
                                @RequestHeader("X-RapidAPI-Key") String apiKey,
                                @RequestHeader("X-RapidAPI-Host") String apiHost);

    @GetMapping("teams?country=Brazil")
    TeamResponseDto consultClubs(@RequestHeader("X-RapidAPI-Key") String apiKey,
                                 @RequestHeader("X-RapidAPI-Host") String apiHost);

}
