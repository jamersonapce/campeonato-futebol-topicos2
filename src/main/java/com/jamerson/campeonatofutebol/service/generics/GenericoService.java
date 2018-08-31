package com.jamerson.campeonatofutebol.service.generics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GenericoService<T> {

    private final JpaRepository<T, Integer> repository;

    GenericoService(JpaRepository<T, Integer> repository ) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    List<T> listaEntities() {
        return repository.findAll();
    }
}
