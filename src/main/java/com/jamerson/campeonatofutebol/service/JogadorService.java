package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Jogador;
import com.jamerson.campeonatofutebol.repository.JogadorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    @Autowired
    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    @Transactional(readOnly = true)
    public List<Jogador> listaJogadores(){
        return this.jogadorRepository.findAll();
    }

    @Transactional
    public Jogador salva(Jogador jogador){
        return this.jogadorRepository.save(jogador);
    }

    @Transactional(readOnly = true)
    public Optional<Jogador> buscaPor(Integer id){
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return Optional.ofNullable(jogador.orElseThrow(() -> new EmptyResultDataAccessException(1))) ;
    }

    @Transactional
    public Jogador atualiza(Integer id, Jogador jogador){
        Optional<Jogador> jogadorRetornado = this.buscaPor(id);
        BeanUtils.copyProperties(jogador, jogadorRetornado, "id");
        this.salva(jogadorRetornado.get());
        return jogadorRetornado.get();
    }

    @Transactional
    public void excluir(Integer id) {
        try {
            jogadorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(1);
        }
    }
}
