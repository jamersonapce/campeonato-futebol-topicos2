package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.repository.CampeonatoRepository;
import com.jamerson.campeonatofutebol.repository.filter.CampeonatoFilter;
import com.jamerson.campeonatofutebol.service.generics.GenericoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;
    private final GenericoService<Campeonato> genericoService;

    @Autowired
    public CampeonatoService(CampeonatoRepository campeonatoRepository){
        this.campeonatoRepository = campeonatoRepository;
        this.genericoService = new GenericoService<Campeonato>(campeonatoRepository );
    }

    @Transactional(readOnly = true)
    public List<Campeonato> listaCampeonatos(){
        return this.genericoService.listaEntities();
    }

    @Transactional
    public Campeonato salva(Campeonato campeonato){
        return this.genericoService.salva(campeonato );
    }

    @Transactional(readOnly = true)
    public Optional<Campeonato> buscaPor(Integer id) {
        return this.genericoService.buscaPor(id);
    }

    @Transactional
    public Campeonato atualiza(Campeonato campeonato, Integer id) {
        return this.genericoService.atualiza(campeonato, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluir(id);
    }


    public List<Campeonato> pesquisa(CampeonatoFilter filter) {
        return campeonatoRepository.filtrar(filter);
    }

    public Page<Campeonato> pesquisa(CampeonatoFilter filter, Pageable pageable) {
        return campeonatoRepository.filtrar(filter, pageable);
    }
}
