package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.BancoEntity;

public class BancoDTO {

    private long id;
    private String nomeBanco;
    private String apelidoBanco;

    public BancoDTO(BancoEntity entity) {
        this.id = entity.getId();
        this.nomeBanco = entity.getNomeBanco();
        this.apelidoBanco = entity.getApelidoBanco();
    }

    public long getId() { return id; }
    public String getNomeBanco() { return nomeBanco; }
    public String getApelidoBanco() { return apelidoBanco; }

}
