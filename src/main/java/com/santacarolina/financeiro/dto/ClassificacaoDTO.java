package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class ClassificacaoDTO implements DataDAO {

    private long id;
    private long categoriaId;
    private FluxoCaixa fluxoCaixa;
    private long numeroIdentificacao;
    private String nomeClassificacao;

    public ClassificacaoDTO(long id, long categoriaId, FluxoCaixa fluxoCaixa, long numeroIdentificacao, String nomeClassificacao) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.fluxoCaixa =fluxoCaixa;
        this.numeroIdentificacao = numeroIdentificacao;
        this.nomeClassificacao = nomeClassificacao;
    }

    @Override
    public long getId() { return id; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public long getNumeroIdentificacao() { return numeroIdentificacao; }
    public String getNomeClassificacao() { return nomeClassificacao; }
    public long getCategoriaId() { return categoriaId; }

    @Override
    public void setId(long id) { this.id = id; }

}
