package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class ClassificacaoDTO implements DataDAO {

    private long id;
    private long categoriaId;
    private String nomeCategoria;
    private FluxoCaixa fluxoCaixa;
    private String numeroIdentificacao;
    private String nomeClassificacao;

    public ClassificacaoDTO(long id, long categoriaId, String nomeCategoria, FluxoCaixa fluxoCaixa, String numeroIdentificacao, 
        String nomeClassificacao) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.nomeCategoria = nomeCategoria;
        this.fluxoCaixa =fluxoCaixa;
        this.numeroIdentificacao = numeroIdentificacao;
        this.nomeClassificacao = nomeClassificacao;
    }

    @Override
    public long getId() { return id; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public String getNumeroIdentificacao() { return numeroIdentificacao; }
    public String getNomeClassificacao() { return nomeClassificacao; }
    public long getCategoriaId() { return categoriaId; }
    public String getNomeCategoria() { return nomeCategoria; }


    @Override
    public void setId(long id) { this.id = id; }

}
