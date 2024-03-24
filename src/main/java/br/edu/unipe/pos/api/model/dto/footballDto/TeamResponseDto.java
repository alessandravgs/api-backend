package br.edu.unipe.pos.api.model.dto.footballDto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TeamResponseDto
{
    public String get;
    public Map<String, String> parameters;
    public List<String> errors;
    public int results;
    public Paging paging;
    public List<TeamInfo> response;
}

