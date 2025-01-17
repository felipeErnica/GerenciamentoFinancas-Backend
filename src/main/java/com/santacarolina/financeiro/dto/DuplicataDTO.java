package com.santacarolina.financeiro.dto;

import java.time.LocalDate;

import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.enums.TipoPagamento;

public class DuplicataDTO {

    private long id;
    private DocumentoDTO documento;
    private Long dadoId;
    private Long pixId;
    private int numDup;
    private TipoPagamento tipoPagamento;
    private LocalDate  dataVencimento;
    private String boletoCaminho;
    private double valor;
    private boolean paga;

    public DuplicataDTO(DuplicataEntity entity) {
        this.id = entity.getId();
        this.documento = entity.getDocumento() != null ? new DocumentoDTO(entity.getDocumento()) : null;
        this.dadoId = entity.getDado() != null ? entity.getDado().getId() : null;
        this.pixId = entity.getPix() != null ? entity.getPix().getId() : null;
        this.numDup = entity.getNumDup();
        this.tipoPagamento = entity.getTipoPagamento();
        this.dataVencimento = entity.getDataVencimento();
        this.boletoCaminho = entity.getBoletoCaminho();
        this.valor = entity.getValor();
        this.paga = entity.isPaga();
    }

    public long getId() { return id; }
    public int getNumDup() { return numDup; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public String getBoletoCaminho() { return boletoCaminho; }
    public double getValor() { return valor; }
    public boolean isPaga() { return paga; }
    public DocumentoDTO getDocumento() { return documento; }
    public Long getDadoId() { return dadoId; }
    public Long getPixId() { return pixId; }

}
