package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ClassificacaoEntity;

public class ClassificacaoDTO {

    private long id;
    private CategoriaDTO categoria;
    private String numeroIdentificacao;
    private String nomeClassificacao;

    public ClassificacaoDTO(ClassificacaoEntity entity) {
        this.id = entity.getId();
        this.categoria = entity.getCategoria() != null ? new CategoriaDTO(entity.getCategoria()) : null;
        this.numeroIdentificacao = entity.getNumeroIdentificacao();
        this.nomeClassificacao = entity.getNomeClassificacao();
    }

    public long getId() { return id; }
    public String getNumeroIdentificacao() { return numeroIdentificacao; }
    public String getNomeClassificacao() { return nomeClassificacao; }
    public CategoriaDTO getCategoria() { return categoria; }

}
