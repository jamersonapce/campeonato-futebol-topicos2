package com.jamerson.campeonatofutebol.controller;


import com.jamerson.campeonatofutebol.model.Campeonato;
import com.jamerson.campeonatofutebol.service.CampeonatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/campeonato")
public class CampeonatoController {

    private final CampeonatoService campeonatoService;

    @Autowired
    public CampeonatoController(CampeonatoService campeonatoService){
        this.campeonatoService = campeonatoService;
    }

    @PostMapping
    public ResponseEntity<Void> salva(@Valid @RequestBody Campeonato campeonato){
        Campeonato campeonatoSalvo = this.campeonatoService.salva(campeonato);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(campeonatoSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> lista(){
        List<Campeonato> campeonatos = this.campeonatoService.listaCampeonatos();
        if(campeonatos.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(campeonatos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPor(@PathVariable Integer id){
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(this.campeonatoService.buscaPor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> atualiza(@PathVariable Integer id, @Valid @RequestBody Campeonato campeonato){
        Campeonato campeonatoResult = this.campeonatoService.atualiza(campeonato, id);
        return  ResponseEntity.ok(campeonatoResult);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        this.campeonatoService.excluir(id);
    }
}
