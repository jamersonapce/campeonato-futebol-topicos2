package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
