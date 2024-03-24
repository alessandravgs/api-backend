package br.edu.unipe.pos.api.repository;

import br.edu.unipe.pos.api.model.baseObjects.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Integer> {
    public List<Clube> findByNomeOrderByNomeAsc(String nome);
}
