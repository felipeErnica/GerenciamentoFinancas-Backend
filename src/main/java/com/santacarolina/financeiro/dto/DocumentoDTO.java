package com.santacarolina.financeiro.dto;

import java.time.LocalDate;

import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;

public class DocumentoDTO {

    private long id;
    private Long numDoc;
    private TipoDocumento tipoDoc;
    private ContatoDTO emissor;
    private String caminhoDocumento;
    private PastaDTO pasta;
    private double valor;
    private LocalDate dataEmissao;
    private FluxoCaixa fluxoCaixa;

    public DocumentoDTO(DocumentoEntity entity) {
        this.id = entity.getId();
        this.numDoc = entity.getNumDoc();
        this.tipoDoc = entity.getTipoDoc();
        this.caminhoDocumento = entity.getCaminhoDocumento();
        this.emissor = entity.getEmissor() != null ? new ContatoDTO(entity.getEmissor()) : null;
        this.pasta = entity.getPasta() != null ? new PastaDTO(entity.getPasta()) : null;
        this.valor = entity.getValor();
        this.dataEmissao = entity.getDataEmissao();
        this.fluxoCaixa = entity.getFluxoCaixa();
    }

    public long getId() { return id; }
    public Long getNumDoc() { return numDoc; }
    public TipoDocumento getTipoDoc() { return tipoDoc; }
    public String getCaminhoDocumento() { return caminhoDocumento; }
    public ContatoDTO getEmissor() { return emissor; }
    public PastaDTO getPasta() { return pasta; }
    public double getValor() { return valor; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }

}
