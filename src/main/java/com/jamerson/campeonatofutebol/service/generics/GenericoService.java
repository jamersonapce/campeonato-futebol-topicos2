package com.jamerson.campeonatofutebol.service.generics;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class GenericoService<T> {

    private final JpaRepository<T, Integer> repository;

    public GenericoService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public List<T> listaEntities() {
        return repository.findAll();
    }

    public T salva(T entity) {
        return repository.save(entity);
    }

    public T atualiza(T entity, Integer id) {
        Optional<T> entityAtBase = this.buscaPor(id);
        BeanUtils.copyProperties(entity, entityAtBase, "id" );
        return this.salva( entityAtBase.get() );
    }

    public Optional<T> buscaPor(Integer id) {
        Optional<T> entity = repository.findById(id);
        return Optional.ofNullable(entity.orElseThrow(() -> new EmptyResultDataAccessException(1)));
    }

    public void excluir(Integer id) {
        try{
            this.repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException(1);
        }
    }
}
