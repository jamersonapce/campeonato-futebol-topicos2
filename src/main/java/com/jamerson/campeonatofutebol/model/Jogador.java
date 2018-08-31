package com.jamerson.campeonatofutebol.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;

    @Column(name = "nascimento")
    @NotNull(message = "A data de nascimento não pode ser nula.")
    @Past
    private LocalDate nascimento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "genero")
    private String genero;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "altura")
    @DecimalMax(value = "2.99",message = "Valor máximo de 2,99m para altura.")
    @DecimalMin(value = "1.5", message = "Valor minimo de 1,5m para altura.")
    @Digits(integer = 1, fraction = 2)
    private double altura;

    @NotEmpty(message = "O time é obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_time")
    private Time time;

    @OneToOne
    @JoinColumn(name = "capitaoDoTime")
    private Time capitao;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getCapitao() {
        return capitao;
    }

    public void setCapitao(Time capitao) {
        this.capitao = capitao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(id, jogador.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
