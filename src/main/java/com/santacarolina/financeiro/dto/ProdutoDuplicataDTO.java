package com.santacarolina.financeiro.dto;

import java.time.LocalDate;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.interfaces.DataDAO;

/**
 * ProdutoDuplicata
 */
public class ProdutoDuplicataDTO implements DataDAO {

    private long prodId;
    private String descricao;
    private String unidade;
    private double quantidade;
    private double valorUnit;
    private long docId;
    private TipoDocumento tipoDoc;
    private Long numDoc;
    private long dupId;
    private LocalDate dataVencimento;
    private long pastaId;
    private String nomePasta;
    private long emissorId;
    private String nomeEmissor;
    private FluxoCaixa fluxoCaixa;
    private long classificacaoId;
    private String classificacao;

    public ProdutoDuplicataDTO(long prodId, String descricao, String unidade, double quantidade, double valorUnit,
            long docId, TipoDocumento tipoDoc, Long numDoc, long dupId, LocalDate dataVencimento, long pastaId,
            String nomePasta, long emissorId, String nomeEmissor, FluxoCaixa fluxoCaixa, long classificacaoId,
            String classificacao) {
        this.prodId = prodId;
        this.descricao = descricao;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.docId = docId;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.dupId = dupId;
        this.dataVencimento = dataVencimento;
        this.pastaId = pastaId;
        this.nomePasta = nomePasta;
        this.emissorId = emissorId;
        this.nomeEmissor = nomeEmissor;
        this.fluxoCaixa = fluxoCaixa;
        this.classificacaoId = classificacaoId;
        this.classificacao = classificacao;
    }

    @Override
    public long getId() { return prodId; }

    public long getDocId() { return docId; }
    public long getDupId() { return dupId; }
    public long getProdId() { return prodId; }
    public long getPastaId() { return pastaId; }
    public String getNomePasta() { return nomePasta; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public TipoDocumento getTipoDoc() { return tipoDoc; }
    public Long getNumDoc() { return numDoc; }
    public long getEmissorId() { return emissorId; }
    public String getNomeEmissor() { return nomeEmissor; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public long getClassificacaoId() { return classificacaoId; }
    public String getClassificacao() { return classificacao; }
    public String getDescricao() { return descricao; }
    public String getUnidade() { return unidade; }
    public double getQuantidade() { return quantidade; }
    public double getValorUnit() { return valorUnit; }

    @Override
    public void setId(long id) { this.prodId = id; }

}
