package com.santacarolina.financeiro.entity;

import java.time.LocalDate;
import java.util.List;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Long numDoc;
    
    @Enumerated(EnumType.ORDINAL)
    private TipoDocumento tipoDoc;

    @Enumerated(EnumType.ORDINAL)
    private FluxoCaixa fluxoCaixa;

    @ManyToOne
    @JoinColumn(name = "emissor_id")
    private ContatoEntity contato;

    @ManyToOne
    @JoinColumn(name = "pasta_id")
    private PastaEntity pasta;

    private String caminhoDocumento;
    private double valor;
    private LocalDate dataEmissao;

    @OneToMany(mappedBy = "documento", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DuplicataEntity> duplicataList;

    @OneToMany(mappedBy = "documento", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProdutoEntity> produtoList;

    public long getId() { return id; }
    public Long getNumDoc() { return numDoc; }
    public TipoDocumento getTipoDoc() { return tipoDoc; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public ContatoEntity getContato() { return contato; }
    public String getCaminhoDocumento() { return caminhoDocumento; }
    public PastaEntity getPasta() { return pasta; }
    public double getValor() { return valor; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public List<DuplicataEntity> getDuplicataList() { return duplicataList; }
    public List<ProdutoEntity> getProdutoList() { return produtoList; }

}
