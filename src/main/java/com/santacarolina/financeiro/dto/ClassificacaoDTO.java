package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ClassificacaoEntity;

public class ClassificacaoDTO {

    private long id;
    private long categoriaId;
    private String numeroIdentificacao;
    private String nomeClassificacao;

    public ClassificacaoDTO(ClassificacaoEntity entity) {
        this.id = entity.getId();
        this.categoriaId = entity.getCategoria() != null ? entity.getCategoria().getId() : 0;
        this.numeroIdentificacao = entity.getNumeroIdentificacao();
        this.nomeClassificacao = entity.getNomeClassificacao();
    }

    public long getId() { return id; }
    public String getNumeroIdentificacao() { return numeroIdentificacao; }
    public String getNomeClassificacao() { return nomeClassificacao; }
    public long getCategoriaId() { return categoriaId; }

}
