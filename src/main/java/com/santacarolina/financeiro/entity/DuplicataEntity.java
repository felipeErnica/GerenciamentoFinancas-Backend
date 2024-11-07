package com.santacarolina.financeiro.entity;

import java.time.LocalDate;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "duplicatas")
public class DuplicataEntity {

    @ManyToOne
    @JoinColumn(name = "documento_id")
    private DocumentoEntity documento;

    @ManyToOne
    @JoinColumn(name = "dado_id")
    private DadoEntity dado;

    @ManyToOne
    @JoinColumn(name = "pix_id")
    private PixEntity pixId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int numDup;
    
    @Enumerated(EnumType.ORDINAL)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.ORDINAL)
    private FluxoCaixa fluxoCaixa;

    private LocalDate  dataVencimento;
    private String nomeContato;
    private String boletoCaminho;
    private double valor;
    private boolean paga;

    public DocumentoEntity getDocumento() { return documento; }
    public DadoEntity getDado() { return dado; }
    public PixEntity getPixId() { return pixId; }
    public long getId() { return id; }
    public int getNumDup() { return numDup; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public String getNomeContato() { return nomeContato; }
    public String getBoletoCaminho() { return boletoCaminho; }
    public double getValor() { return valor; }
    public boolean isPaga() { return paga; }

}

