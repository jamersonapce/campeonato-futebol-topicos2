package com.jamerson.campeonatofutebol.repository;

import com.jamerson.campeonatofutebol.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer> {
}
