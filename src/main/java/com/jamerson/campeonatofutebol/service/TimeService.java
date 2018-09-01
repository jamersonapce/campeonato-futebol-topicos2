package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Time;
import com.jamerson.campeonatofutebol.repository.TimeRepository;
import com.jamerson.campeonatofutebol.service.generics.GenericoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    private final GenericoService<Time> genericoService;

    @Autowired
    public TimeService(TimeRepository timeRepository){
        this.genericoService = new GenericoService<Time>(timeRepository);
    }

    @Transactional(readOnly = true)
    public List<Time> lista(){
        return this.genericoService.listaEntities();
    }

    @Transactional
    public Time salva(Time time){
        return this.genericoService.salva(time);
    }

    @Transactional(readOnly = true)
    public Optional<Time> buscaPro(Integer id){
        return  this.genericoService.buscaPor(id);
    }

    @Transactional
    public Time atualiza(Time time, Integer id){
        return this.genericoService.atualiza(time, id);
    }

    @Transactional
    public void excluir(Integer id){
        this.genericoService.excluir(id);
    }
}
