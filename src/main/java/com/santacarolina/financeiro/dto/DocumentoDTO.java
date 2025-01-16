package com.santacarolina.financeiro.dto;

import java.time.LocalDate;

import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;

public class DocumentoDTO {

    private long id;
    private Long numDoc;
    private TipoDocumento tipoDoc;
    private Long emissorId;
    private String caminhoDocumento;
    private Long pastaId;
    private double valor;
    private LocalDate dataEmissao;
    private FluxoCaixa fluxoCaixa;

    public DocumentoDTO(DocumentoEntity entity) {
        this.id = entity.getId();
        this.numDoc = entity.getNumDoc();
        this.tipoDoc = entity.getTipoDoc();
        this.emissorId = entity.getContato() != null ? entity.getContato().getId() : 0;
        this.caminhoDocumento = entity.getCaminhoDocumento();
        this.pastaId = entity.getPasta() != null ? entity.getPasta().getId() : 0;
        this.valor = entity.getValor();
        this.dataEmissao = entity.getDataEmissao();
        this.fluxoCaixa = entity.getFluxoCaixa();
    }

    public long getId() { return id; }
    public Long getNumDoc() { return numDoc; }
    public TipoDocumento getTipoDoc() { return tipoDoc; }
    public Long getEmissorId() { return emissorId; }
    public String getCaminhoDocumento() { return caminhoDocumento; }
    public long getPastaId() { return pastaId; }
    public double getValor() { return valor; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }

}
