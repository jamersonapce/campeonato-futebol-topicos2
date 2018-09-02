package com.jamerson.campeonatofutebol.repository.campeonato;

import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.repository.filter.CampeonatoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampeonatoRepositoryQuery {
    List<Campeonato> filtrar(CampeonatoFilter campeonatoFilter);
    Page<Campeonato> filtrar(CampeonatoFilter filter, Pageable pageable);
}
