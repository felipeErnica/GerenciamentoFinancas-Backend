package com.santacarolina.financeiro.dto;

import java.time.LocalDate;

import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.enums.TipoPagamento;

public class DuplicataDTO {

    private long id;
    private Long docId;
    private Long dadoId;
    private Long pixId;
    private int numDup;
    private TipoPagamento tipoPagamento;
    private LocalDate  dataVencimento;
    private String boletoCaminho;
    private double valor;
    private boolean paga;

    public DuplicataDTO(DuplicataEntity entity) {
        this.docId = entity.getDocumento() != null ? entity.getDocumento().getId() : null;
        this.dadoId = entity.getDado() != null ? entity.getDado().getId() : null;
        this.id = entity.getId();
        this.numDup = entity.getNumDup();
        this.tipoPagamento = entity.getTipoPagamento();
        this.dataVencimento = entity.getDataVencimento();
        this.boletoCaminho = entity.getBoletoCaminho();
        this.valor = entity.getValor();
        this.paga = entity.isPaga();
        this.pixId = entity.getPix() != null ? entity.getPix().getId() : null;
    }

    public long getId() { return id; }
    public Long getDocId() { return docId; }
    public Long getDadoId() { return dadoId; }
    public int getNumDup() { return numDup; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public String getBoletoCaminho() { return boletoCaminho; }
    public double getValor() { return valor; }
    public boolean isPaga() { return paga; }
    public Long getPixId() { return pixId; }

}
