package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.interfaces.DataDAO;

/**
 * CategoriaDTO
 */
public class CategoriaDTO implements DataDAO {

    private long id;
    private FluxoCaixa fluxoCaixa;
    private String nome;

    public CategoriaDTO(long id, FluxoCaixa fluxoCaixa, String nome) {
        this.id = id;
        this.fluxoCaixa = fluxoCaixa;
        this.nome = nome;
    }

    public long getId() { return id; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public String getNome() { return nome; }

    @Override
    public void setId(long id) { this.id = id; }

    public void setFluxoCaixa(FluxoCaixa fluxoCaixa) { this.fluxoCaixa = fluxoCaixa; }
    public void setNome(String nome) { this.nome = nome; }

}
