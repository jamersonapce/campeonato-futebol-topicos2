package com.jamerson.campeonatofutebol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "time")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", unique = true)
    @NotEmpty(message = "Campo nome é obrigatório.")
    @Size(max = 50, message = "Informe um nome com até 50 caracteres.")
    private String nome;

    @NotEmpty(message = "Campo estado obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_estado")
    private Estado estado;

    @ManyToMany(mappedBy = "times", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Campeonato> campeonatos = new ArrayList<>();

    @JsonProperty("partidas visitante")
    @OneToMany(mappedBy = "timeVisitante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Partida> partidasComoVisitante = new ArrayList<>();

    @JsonProperty("partidas mandante")
    @OneToMany(mappedBy = "timeMandante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Partida> partidasComoMandante = new ArrayList<>();

    @JsonProperty("jogadores")
    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Jogador> jogadores = new ArrayList<>();

    @OneToOne(mappedBy = "capitaoDoTime", fetch=FetchType.LAZY, optional=false)
    private Jogador capitaoDoTime;

    public Integer getId() {
        return id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Campeonato> getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(List<Campeonato> campeonatos) {
        this.campeonatos = campeonatos;
    }

    public List<Partida> getPartidasComoVisitante() {
        return partidasComoVisitante;
    }

    public void setPartidasComoVisitante(List<Partida> partidasComoVisitante) {
        this.partidasComoVisitante = partidasComoVisitante;
    }

    public List<Partida> getPartidasComoMandante() {
        return partidasComoMandante;
    }

    public void setPartidasComoMandante(List<Partida> partidasComoMandante) {
        this.partidasComoMandante = partidasComoMandante;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Jogador getCapitaoDoTime() {
        return capitaoDoTime;
    }

    public void setCapitaoDoTime(Jogador capitaoDoTime) {
        this.capitaoDoTime = capitaoDoTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(id, time.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
