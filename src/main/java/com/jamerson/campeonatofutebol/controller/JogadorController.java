package com.jamerson.campeonatofutebol.controller;


import com.jamerson.campeonatofutebol.model.Jogador;
import com.jamerson.campeonatofutebol.service.JogadorService;
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
@RequestMapping("/jogador")
public class JogadorController {

    private JogadorService jogadorService;

    @Autowired
    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    public ResponseEntity<Void> salva(@Valid @RequestBody Jogador jogador){
        Jogador jogadorSalvo = jogadorService.salva(jogador);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(jogadorSalvo.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> lista(){
        List<Jogador> jogadores = jogadorService.listaJogadores();
        if(jogadores.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(jogadores);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPor(@PathVariable Integer id){
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(jogadorService.buscaPor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualiza(@PathVariable Integer id, @Valid @RequestBody Jogador jogador){
        Jogador campeonatoResult = jogadorService.atualiza(id, jogador);
        return  ResponseEntity.ok(campeonatoResult);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        jogadorService.excluir(id);
    }

}
