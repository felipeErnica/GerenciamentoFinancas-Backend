package com.santacarolina.financeiro.entity;

import java.time.LocalDate;
import java.util.List;

import com.santacarolina.financeiro.enums.TipoPagamento;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "duplicatas")
public class DuplicataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private int numDup;
    
    @Enumerated(EnumType.ORDINAL)
    private TipoPagamento tipoPagamento;

    private LocalDate  dataVencimento;
    private String boletoCaminho;
    private double valor;
    private boolean paga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id")
    private DocumentoEntity documento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dado_id")
    private DadoEntity dado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pix_id")
    private PixEntity pix;

    @OneToMany(mappedBy = "duplicata", cascade = CascadeType.ALL)
    private List<ConciliacaoEntity> conciliacaoList;

    public DocumentoEntity getDocumento() { return documento; }
    public DadoEntity getDado() { return dado; }
    public PixEntity getPix() { return pix; }
    public long getId() { return id; }
    public int getNumDup() { return numDup; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public String getBoletoCaminho() { return boletoCaminho; }
    public double getValor() { return valor; }
    public boolean isPaga() { return paga; }

    public void setDocumento(DocumentoEntity documento) { this.documento = documento; }

}

