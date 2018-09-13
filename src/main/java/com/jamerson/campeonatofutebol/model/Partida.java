package com.jamerson.campeonatofutebol.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table (name="partida")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @JsonProperty("data da partida")
    @Column(name = "data_partida")
    @NotNull(message = "A data da partida não pode ser nula.")
    @PastOrPresent
    private LocalDate dataPartida;

    @JsonProperty("pontuação mandante")
    @Column(name = "pontuacao_mandante", columnDefinition = "int default 0")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @PositiveOrZero(message = "A Pontuação não pode ser negativa.")
    private Integer pontuacaoMandante;

    @JsonProperty("pontuação visitante")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "pontuacao_visitante", columnDefinition = "int default 0")
    @PositiveOrZero(message = "A pontuação não pode ser negativa.")
    private Integer pontuacaoVisitante;

    @NotEmpty(message = "Campeonato é obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_campeonato")
    private Campeonato campeonato;

    @NotEmpty(message = "Time visitante é obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_time_visitante")
    private Time timeVisitante;

    @NotEmpty(message = "Time mandante é obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_time_mandante")
    private Time timeMandante;

    public Partida(){}

    public Partida(LocalDate dataPartida, Integer pontuacaoMandante, Integer pontuacaoVisitante, Campeonato campeonato) {
        this.dataPartida = dataPartida;
        this.pontuacaoMandante = pontuacaoMandante;
        this.pontuacaoVisitante = pontuacaoVisitante;
        this.campeonato = campeonato;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Integer getPontuacaoMandante() {
        return pontuacaoMandante;
    }

    public void setPontuacaoMandante(Integer pontuacaoMandante) {
        this.pontuacaoMandante = pontuacaoMandante;
    }

    public Integer getPontuacaoVisitante() {
        return pontuacaoVisitante;
    }

    public void setPontuacaoVisitante(Integer pontuacaoVisitante) {
        this.pontuacaoVisitante = pontuacaoVisitante;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public Time getTimeMandante() {
        return timeMandante;
    }

    public void setTimeMandante(Time timeMandante) {
        this.timeMandante = timeMandante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partida partida = (Partida) o;
        return Objects.equals(Id, partida.Id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Id);
    }
}
