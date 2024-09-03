package com.santacarolina.financeiro.models.entities;

import com.santacarolina.financeiro.models.enums.FluxoCaixa;
import jakarta.persistence.*;

@Entity
@Table(name = "classificacoes_contabeis")
public class ClassificacaoContabil {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Enumerated(EnumType.ORDINAL)
    private FluxoCaixa fluxoCaixa;
    private long numeroIdentificacao;
    private String nomeClassificacao;

    public long getId() { return id; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public long getNumeroIdentificacao() { return numeroIdentificacao; }
    public String getNomeClassificacao() { return nomeClassificacao; }

}


