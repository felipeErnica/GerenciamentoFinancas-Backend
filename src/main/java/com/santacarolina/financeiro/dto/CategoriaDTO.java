package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.CategoriaEntity;
import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.interfaces.DataDAO;

/**
 * CategoriaDTO
 */
public class CategoriaDTO implements DataDAO {

    private long id;
    private FluxoCaixa fluxoCaixa;
    private String numeroCategoria;
    private String nome;

    public CategoriaDTO(CategoriaEntity entity) {
        this.id = entity.getId();
        this.fluxoCaixa = entity.getFluxoCaixa();
        this.numeroCategoria = entity.getNumeroCategoria();
        this.nome = entity.getNome();
    }

    public long getId() { return id; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public String getNome() { return nome; }
    public String getNumeroCategoria() { return numeroCategoria; }

    @Override
    public void setId(long id) { this.id = id; }

    public void setFluxoCaixa(FluxoCaixa fluxoCaixa) { this.fluxoCaixa = fluxoCaixa; }
    public void setNome(String nome) { this.nome = nome; }

}
