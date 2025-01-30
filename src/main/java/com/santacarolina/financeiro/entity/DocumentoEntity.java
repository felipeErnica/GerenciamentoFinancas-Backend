package com.santacarolina.financeiro.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;

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

/**
 * DocumentoEntity
 */
@Entity
@Table(name = "documentos")
public class DocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private Long numDoc;

    @Enumerated(EnumType.ORDINAL)
    private TipoDocumento tipoDoc;

    @Enumerated(EnumType.ORDINAL)
    private FluxoCaixa fluxoCaixa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emissor_id")
    private ContatoEntity emissor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pasta_id")
    private PastaEntity pasta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String caminhoDocumento;
    private double valor;
    private LocalDate dataEmissao;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DuplicataEntity> duplicataList = new ArrayList<>();

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoEntity> produtoList = new ArrayList<>();

    public long getId() { return id; }
    public Long getNumDoc() { return numDoc; }
    public TipoDocumento getTipoDoc() { return tipoDoc; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public ContatoEntity getEmissor() { return emissor; }
    public String getCaminhoDocumento() { return caminhoDocumento; }
    public PastaEntity getPasta() { return pasta; }
    public double getValor() { return valor; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public List<DuplicataEntity> getDuplicataList() { return duplicataList; }
    public List<ProdutoEntity> getProdutoList() { return produtoList; }

}
