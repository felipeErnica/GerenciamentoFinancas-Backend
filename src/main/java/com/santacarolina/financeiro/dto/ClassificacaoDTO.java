package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ClassificacaoEntity;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class ClassificacaoDTO implements DataDAO {

    private long id;
    private long categoriaId;
    private String numeroIdentificacao;
    private String nomeClassificacao;

    public ClassificacaoDTO(ClassificacaoEntity entity) {
        this.id = entity.getId();
        this.categoriaId = entity.getCategoria().getId();
        this.numeroIdentificacao = entity.getNumeroIdentificacao();
        this.nomeClassificacao = entity.getNomeClassificacao();
    }

    @Override
    public long getId() { return id; }
    public String getNumeroIdentificacao() { return numeroIdentificacao; }
    public String getNomeClassificacao() { return nomeClassificacao; }
    public long getCategoriaId() { return categoriaId; }

    @Override
    public void setId(long id) { this.id = id; }

}
