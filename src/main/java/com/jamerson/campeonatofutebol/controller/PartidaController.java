package com.jamerson.campeonatofutebol.controller;

import com.jamerson.campeonatofutebol.model.Partida;
import com.jamerson.campeonatofutebol.service.PartidaService;
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
@RequestMapping("/partida")
public class PartidaController {

    private final PartidaService partidaService;

    @Autowired
    public PartidaController(PartidaService partidaService){
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<Void> salva(@Valid @RequestBody Partida partida){
        Partida partidaSalva = this.partidaService.salva(partida);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(partidaSalva.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> lista() {
        List<Partida> partidas = this.partidaService.lista();
        if (partidas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(partidas);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPor(@PathVariable Integer id) {
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(this.partidaService.buscaPor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partida> atualiza(@PathVariable Integer id, @Valid @RequestBody Partida partida){
        Partida partidaResult = this.partidaService.atualiza(partida, id);
        return ResponseEntity.ok(partidaResult);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        this.partidaService.excluir(id);
    }
}
