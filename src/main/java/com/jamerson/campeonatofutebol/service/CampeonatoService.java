package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.repository.CampeonatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Campeonato buscaPor(@PathVariable Integer id) {
        Optional<Campeonato> campeonatoOptional = campeonatoRepository.findById(id);
        return campeonatoOptional
                .orElseThrow( () -> new EmptyResultDataAccessException(1) );
    }

    @Transactional
    public Campeonato atualiza(Integer id, Campeonato campeonato) {

        Campeonato campeonatoResult = this.buscaPor(id );
        if( campeonatoResult == null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(campeonato, campeonatoResult, "id");
        this.salva(campeonatoResult);
        return campeonatoResult;
    }

    @Transactional
    public void excluir(Integer id) {
        campeonatoRepository.deleteById(id);
    }
}
