package com.jamerson.campeonatofutebol.service;

import com.jamerson.campeonatofutebol.model.Estado;
import com.jamerson.campeonatofutebol.repository.EstadoRepositry;
import com.jamerson.campeonatofutebol.service.generics.GenericoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService{

    private final GenericoService<Estado> genericoService;

    @Autowired
    public EstadoService(EstadoRepositry estadoRepositry) {
        this.genericoService = new GenericoService<Estado>(estadoRepositry);
    }

    @Transactional(readOnly = true)
    public List<Estado> lista(){
        return this.genericoService.listaEntities();
    }

    @Transactional
    public Estado salva(Estado estado){
        return this.genericoService.salva(estado);
    }

    @Transactional(readOnly = true)
    public Optional<Estado> buscaPor(Integer id){
        return this.genericoService.buscaPor(id);
    }

    @Transactional
    public Estado atualiza(Estado estado, Integer id){
        return this.genericoService.atualiza(estado, id);
    }

    @Transactional
    public void excluir(Integer id){
        this.genericoService.excluir(id);
    }
}
