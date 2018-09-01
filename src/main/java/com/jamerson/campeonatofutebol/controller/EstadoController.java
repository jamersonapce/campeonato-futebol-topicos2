package com.jamerson.campeonatofutebol.controller;

import com.jamerson.campeonatofutebol.model.Estado;
import com.jamerson.campeonatofutebol.service.EstadoService;
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
@RequestMapping("/estado")
public class EstadoController {

    private EstadoService estadoService;

    @Autowired
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @PostMapping
    public ResponseEntity<Void> salva(@Valid @RequestBody Estado estado) {
        Estado estadoSalvo = this.estadoService.salva(estado);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(estadoSalvo.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> lista() {
        List<Estado> estados = this.estadoService.lista();
        if (estados.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(estados);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaPor(@PathVariable Integer id) {
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(this.estadoService.buscaPor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualiza(@PathVariable Integer id, @Valid @RequestBody Estado estado) {
        Estado estadoResult = this.estadoService.atualiza(estado, id);
        return ResponseEntity.ok(estadoResult);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        this.estadoService.excluir(id);
    }
}
