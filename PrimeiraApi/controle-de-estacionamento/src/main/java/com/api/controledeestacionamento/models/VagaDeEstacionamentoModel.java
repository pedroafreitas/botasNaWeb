package com.api.controledeestacionamento.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_VAGA_DE_ESTACIONAMENTO")
public class VagaDeEstacionamentoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String numeroDaVaga;

    @Column(nullable = false, unique = true, length = 7)
    private String placaDoCarro;

    @Column(nullable = false, length = 70)
    private String marcaDoCarro;

    @Column(nullable = false, length = 70)
    private String corDoCarro;

    @Column(nullable = false)
    private LocalDateTime dataDoRegistro;

    @Column(nullable = false, length = 150)
    private String nomeDoResponsavel;

    @Column(nullable = false, length = 10)
    private String apartamento;

    @Column(nullable = false, length = 10)
    private String bloco;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroDaVaga() {
        return numeroDaVaga;
    }

    public void setNumeroDaVaga(String numeroDaVaga) {
        this.numeroDaVaga = numeroDaVaga;
    }

    public String getPlacaDoCarro() {
        return placaDoCarro;
    }

    public void setPlacaDoCarro(String placaDoCarro) {
        this.placaDoCarro = placaDoCarro;
    }

    public String getMarcaDoCarro() {
        return marcaDoCarro;
    }

    public void setMarcaDoCarro(String marcaDoCarro) {
        this.marcaDoCarro = marcaDoCarro;
    }

    public String getCorDoCarro() {
        return corDoCarro;
    }

    public void setCorDoCarro(String corDoCarro) {
        this.corDoCarro = corDoCarro;
    }

    public LocalDateTime getDataDoRegistro() {
        return dataDoRegistro;
    }

    public void setDataDoRegistro(LocalDateTime dataDoRegistro) {
        this.dataDoRegistro = dataDoRegistro;
    }

    public String getNomeDoResponsavel() {
        return nomeDoResponsavel;
    }

    public void setNomeDoResponsavel(String nomeDoResponsavel) {
        this.nomeDoResponsavel = nomeDoResponsavel;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }
}
