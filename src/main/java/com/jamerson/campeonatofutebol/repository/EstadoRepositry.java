package com.jamerson.campeonatofutebol.repository;

import com.jamerson.campeonatofutebol.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepositry extends JpaRepository<Estado, Integer> {
}
