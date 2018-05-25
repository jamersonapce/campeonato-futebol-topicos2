package com.jamerson.campeonatofutebol.controller;


import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.service.CampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    private final CampeonatoService campeonatoService;


    @Autowired
    public CampeonatoController(CampeonatoService campeonatoService){
        this.campeonatoService = campeonatoService;
    }


    @GetMapping
    public ResponseEntity<?> listaCampeonatos(){

        List<Campeonato> campeonatos = campeonatoService.listaCampeonatos();

        if(campeonatos.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(campeonatos);
        }
    }
}
