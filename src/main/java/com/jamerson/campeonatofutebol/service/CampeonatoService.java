package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.repository.CampeonatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CampeonatoService {

    private final CampeonatoRepository campeonatoRepository;

    @Autowired
    public CampeonatoService(CampeonatoRepository campeonatoRepository){
        this.campeonatoRepository = campeonatoRepository;
    }


    @Transactional(readOnly = true)
    public List<Campeonato> listaCampeonatos(){
        return campeonatoRepository.findAll();
    }


    @Transactional
    public Campeonato salva(Campeonato campeonato){
        return this.campeonatoRepository.save(campeonato);
    }

    @Transactional(readOnly = true)
    public Optional<Campeonato> buscaPor(Integer id) {
        Optional<Campeonato> campeonato = campeonatoRepository.findById(id);
        return Optional.ofNullable(campeonato.orElseThrow(() -> new EmptyResultDataAccessException(1)));
    }

    @Transactional
    public Campeonato atualiza(Integer id, Campeonato campeonato) {
        Optional<Campeonato> campeonatoRetornado = this.buscaPor(id);
        BeanUtils.copyProperties(campeonato, campeonatoRetornado, "id");
        return campeonatoRepository.save(campeonatoRetornado.get());
    }

    @Transactional
    public void excluir(Integer id) {
        try{
            campeonatoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException(1);
        }

    }
}
