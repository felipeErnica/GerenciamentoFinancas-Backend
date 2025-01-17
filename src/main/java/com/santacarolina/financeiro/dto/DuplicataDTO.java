package com.santacarolina.financeiro.dto;

import java.time.LocalDate;

import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.enums.TipoPagamento;

public class DuplicataDTO {

    private long id;
    private DocumentoDTO documento;
    private DadoDTO dado;
    private PixDTO pix;
    private int numDup;
    private TipoPagamento tipoPagamento;
    private LocalDate  dataVencimento;
    private String boletoCaminho;
    private double valor;
    private boolean paga;

    public DuplicataDTO(DuplicataEntity entity) {
        this.id = entity.getId();
        this.documento = entity.getDocumento() != null ? new DocumentoDTO(entity.getDocumento()) : null;
        this.dado = entity.getDado() != null ? new DadoDTO(entity.getDado()) : null;
        this.pix = entity.getPix() != null ? new PixDTO(entity.getPix()) : null;
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
    public DadoDTO getDado() { return dado; }
    public PixDTO getPix() { return pix; }

}
