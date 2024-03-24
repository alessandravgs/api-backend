package br.edu.unipe.pos.api.service;

import br.edu.unipe.pos.api.model.baseObjects.Clube;
import br.edu.unipe.pos.api.model.dto.ClubeDto;
import br.edu.unipe.pos.api.model.dto.footballDto.TeamInfo;
import br.edu.unipe.pos.api.repository.ClubeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ClubeService {

    private ClubeRepository repository;

    public Clube salvarClube(Clube clube){return repository.save(clube);}
    public List<Clube> listarClubes(){return repository.findAll();}
    public void excluirClube(Integer id){ repository.deleteById(id);}
    public Clube consultarByID(Integer id)
    {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
    public List<Clube> consultarByNome(String nome)
    {
        return repository.findByNomeOrderByNomeAsc(nome);
    }
    public Clube alterarClube(Clube clube)
    {
        if(Objects.isNull(clube.getId()))
        {
            throw new RuntimeException();
        }
        return repository.save(clube);
    }

    public Clube createClubeByDto(ClubeDto clubeDto)
    {
        Clube clube = new Clube();
        clube.setNome(clubeDto.getNome());
        clube.setEstado(clubeDto.getEstado());
        clube.setEmail(clubeDto.getEmail());
        clube.setCnpj(clubeDto.getCnpj());
        clube.setDataCriacao(clubeDto.getDataCriacao());
        return clube;
    }

    public Clube createClubeByFootball(TeamInfo teamInfo)
    {
        Clube clube = new Clube();
        clube.setCnpj("12.123.123/0001-01");
        clube.setDataCriacao(new Date());
        clube.setEstado(teamInfo.venue.city);
        clube.setEmail(teamInfo.team.name+"@email.com");
        clube.setNome(teamInfo.team.name);
        return clube;
    }
}
