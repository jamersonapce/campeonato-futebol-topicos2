package com.jamerson.campeonatofutebol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "campeonato")
public class Campeonato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "nome")
    @NotBlank(message = "O nome não pode ser nulo.")
    @Size(max = 50, message = "Informe um nome com até 50 caracteres.")
    private String nome;


    @Column(name = "ano")
    @NotNull(message = "O ano não pode ser nulo.")
    @Positive (message = "O ano não pode ser negativo.")
    private Integer ano;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Partida> partidas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="campeonato_tem_times", joinColumns=
            {@JoinColumn(name="campeonato_id", referencedColumnName = "id")}, inverseJoinColumns=
            {@JoinColumn(name="time_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<Time> times = new ArrayList<>();

    public Campeonato(){}

    public Campeonato(String nome, Integer ano){
        this.nome = nome;
        this.ano = ano;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campeonato that = (Campeonato) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
