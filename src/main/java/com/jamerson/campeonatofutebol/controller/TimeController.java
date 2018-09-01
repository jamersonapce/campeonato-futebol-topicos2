package com.jamerson.campeonatofutebol.controller;

import com.jamerson.campeonatofutebol.model.Time;
import com.jamerson.campeonatofutebol.service.TimeService;
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
@RequestMapping("/time")
public class TimeController {

    private final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService){
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<Void> salva(@Valid @RequestBody Time time){
        Time timeSalvo = this.timeService.salva(time);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(timeSalvo.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> lista(){
        List<Time> times = this.timeService.lista();
        if(times.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(times);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscaPor(@PathVariable Integer id){
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(this.timeService.buscaPro(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Time> atualiza(@PathVariable Integer id, @Valid @RequestBody Time time){
        Time timeResult = this.timeService.atualiza(time, id);
        return ResponseEntity.ok(timeResult);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        this.timeService.excluir(id);
    }
}
