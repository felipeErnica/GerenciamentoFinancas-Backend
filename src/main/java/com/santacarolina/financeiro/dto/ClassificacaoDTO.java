package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.interfaces.DataDAO;
import jakarta.persistence.*;

public class ClassificacaoDTO implements DataDAO {

    private long id;
    private FluxoCaixa fluxoCaixa;
    private long numeroIdentificacao;
    private String nomeClassificacao;

    public ClassificacaoDTO(long id, FluxoCaixa fluxoCaixa, long numeroIdentificacao, String nomeClassificacao) {
        this.id = id;
        this.fluxoCaixa =fluxoCaixa;
        this.numeroIdentificacao = numeroIdentificacao;
        this.nomeClassificacao = nomeClassificacao;
    }

    @Override
    public long getId() { return id; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public long getNumeroIdentificacao() { return numeroIdentificacao; }
    public String getNomeClassificacao() { return nomeClassificacao; }

}
