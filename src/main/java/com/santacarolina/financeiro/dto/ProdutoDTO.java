package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.interfaces.DataDAO;

import java.time.LocalDate;

public class ProdutoDTO implements DataDAO {

    private long id;
    private long docId;
    private long classificacaoId;
    private String descricao;
    private String und;
    private double quantidade;
    private double valorUnit;
    private LocalDate dataEmissao;
    private long pastaId;
    private String nomePasta;
    private long emissorId;
    private String nomeContato;
    private String classificacao;

    public ProdutoDTO(long id, long docId, long classificacaoId, String descricao, String und, double quantidade,
                      double valorUnit, LocalDate dataEmissao, long pastaId, String nomePasta, long emissorId, String nomeContato, String classificacao) {
        this.id = id;
        this.docId = docId;
        this.classificacaoId = classificacaoId;
        this.descricao = descricao;
        this.und = und;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.dataEmissao = dataEmissao;
        this.pastaId = pastaId;
        this.nomePasta = nomePasta;
        this.emissorId = emissorId;
        this.nomeContato = nomeContato;
        this.classificacao = classificacao;
    }

    @Override
    public long getId() { return id; }
    public long getDocId() { return docId; }
    public long getClassificacaoId() { return classificacaoId; }
    public String getDescricao() { return descricao; }
    public String getUnd() { return und; }
    public double getQuantidade() { return quantidade; }
    public double getValorUnit() { return valorUnit; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public String getNomePasta() { return nomePasta; }
    public String getNomeContato() { return nomeContato; }
    public String getClassificacao() { return classificacao; }
    public long getPastaId() { return pastaId; }
    public long getEmissorId() { return emissorId; }

    @Override
    public void setId(long id) { this.id = id; }
    public void setDocId(long docId) { this.docId = docId; }

}
