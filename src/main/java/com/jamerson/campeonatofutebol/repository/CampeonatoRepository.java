package com.jamerson.campeonatofutebol.repository;

import com.jamerson.campeonatofutebol.model.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Integer> {
}
