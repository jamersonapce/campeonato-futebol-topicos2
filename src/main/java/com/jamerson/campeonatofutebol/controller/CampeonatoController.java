package com.jamerson.campeonatofutebol.controller;


import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.service.CampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<?> lista(){
        List<Campeonato> campeonatos = campeonatoService.listaCampeonatos();

        if(campeonatos.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(campeonatos);
        }
    }


    @PostMapping
    public ResponseEntity<Void> cria(@Validated @RequestBody Campeonato campeonato){
        Campeonato campeonatoSalvo = campeonatoService.salva(campeonato);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(campeonatoSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/{id}")
    public Campeonato buscaPor(@PathVariable Integer id){
        return campeonatoService.buscaPor(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> atualiza(@PathVariable Integer id, @Validated @RequestBody Campeonato campeonato){
        Campeonato campeonatoResult = campeonatoService.atualiza(id, campeonato);
        return  ResponseEntity.ok(campeonatoResult);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        campeonatoService.excluir(id);
    }
}
