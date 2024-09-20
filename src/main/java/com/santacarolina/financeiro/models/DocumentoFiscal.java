package com.santacarolina.financeiro.models;

import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.enums.FluxoCaixa;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class DocumentoFiscal {

    private long id;
    private String docNumero;
    private TipoDocumento docTipo;
    private Contato emissor;
    private String caminhoDocumento;
    private PastaContabil pastaContabil;
    private double valor;
    private LocalDate dataEmissao;
    private FluxoCaixa fluxoCaixa;

    public long getId() { return id; }
    public String getDocNumero() { return docNumero; }
    public TipoDocumento getDocTipo() { return docTipo; }
    public Contato getContato() { return emissor; }
    public String getCaminhoDocumento() { return caminhoDocumento; }
    public PastaContabil getPastaContabil() { return pastaContabil; }
    public double getValor() { return valor; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public FluxoCaixa getFluxoCaixa() { return valor > 0 ? FluxoCaixa.RECEITA : FluxoCaixa.DESPESA; }

    public void setId(long id) { this.id = id; }
    public void setDocNumero(String docNumero) { this.docNumero = docNumero; }
    public void setDocTipo(TipoDocumento docTipo) { this.docTipo = docTipo; }
    public void setEmissor(Contato emissor) { this.emissor = emissor; }
    public void setCaminhoDocumento(String caminhoDocumento) { this.caminhoDocumento = caminhoDocumento; }
    public void setPastaContabil(PastaContabil pastaContabil) { this.pastaContabil = pastaContabil; }
    public void setValor(double valor) { this.valor = valor; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }
    public void setFluxoCaixa(FluxoCaixa fluxoCaixa) { this.fluxoCaixa = fluxoCaixa; }

    public DocumentoDTO toDTO() {
        return new DocumentoDTO(id,
                docNumero,
                docTipo,
                emissor != null ? emissor.getId() : null,
                caminhoDocumento,
                pastaContabil != null ? pastaContabil.getId() : null,
                valor,
                dataEmissao.toString(),
                fluxoCaixa);
    }

}