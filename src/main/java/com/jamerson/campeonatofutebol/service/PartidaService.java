package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Partida;
import com.jamerson.campeonatofutebol.repository.PartidaRepository;
import com.jamerson.campeonatofutebol.service.generics.GenericoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {

    private GenericoService<Partida> genericoService;

    @Autowired
    public PartidaService(PartidaRepository partidaRpository){
        this.genericoService = new GenericoService<Partida>(partidaRpository);
    }

    @Transactional(readOnly = true)
    public List<Partida> lista(){
        return this.genericoService.listaEntities();
    }

    @Transactional
    public Partida salva(Partida partida){
        return this.genericoService.salva(partida);
    }

    @Transactional(readOnly = true)
    public Optional<Partida> buscaPor(Integer id){
        return this.genericoService.buscaPor(id);
    }

    @Transactional
    public Partida atualiza(Partida partida, Integer id){
        return this.genericoService.atualiza(partida, id);
    }

    @Transactional
    public void excluir(Integer id){
        this.genericoService.excluir(id);
    }
}

