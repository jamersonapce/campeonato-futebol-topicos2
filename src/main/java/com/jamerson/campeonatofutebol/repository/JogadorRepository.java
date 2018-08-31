package com.jamerson.campeonatofutebol.repository;

import com.jamerson.campeonatofutebol.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

}
