package com.jamerson.campeonatofutebol.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "time")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @OneToMany(mappedBy = "times", cascade={CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Campeonato> jogadores = new ArrayList<>();

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "capitao", unique=true, updatable=false)
    private Campeonato capitao;

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

    public List<Campeonato> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Campeonato> jogadores) {
        this.jogadores = jogadores;
    }

    public Campeonato getCapitao() {
        return capitao;
    }

    public void getCapitao(Campeonato capitao) {
        this.capitao = capitao;
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
