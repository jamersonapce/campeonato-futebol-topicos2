package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Jogador;
import com.jamerson.campeonatofutebol.repository.JogadorRepository;
import com.jamerson.campeonatofutebol.service.generics.GenericoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    private final GenericoService<Jogador> genericoService;
    @Autowired
    public JogadorService(JogadorRepository jogadorRepository) {
        this.genericoService = new GenericoService<Jogador>(jogadorRepository);
    }

    @Transactional(readOnly = true)
    public List<Jogador> listaJogadores(){
        return this.genericoService.listaEntities();
    }

    @Transactional
    public Jogador salva(Jogador jogador){
        return this.genericoService.salva(jogador);
    }

    @Transactional(readOnly = true)
    public Optional<Jogador> buscaPor(Integer id){
        return this.genericoService.buscaPor(id );
    }

    @Transactional
    public Jogador atualiza(Jogador jogador, Integer id){
        return this.genericoService.atualiza(jogador, id);
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoService.excluir(id);
    }
}
