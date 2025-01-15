package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.BancoEntity;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class BancoDTO implements DataDAO {

    private long id;
    private String nomeBanco;
    private String apelidoBanco;

    public BancoDTO(BancoEntity entity) {
        this.id = entity.getId();
        this.nomeBanco = entity.getNomeBanco();
        this.apelidoBanco = entity.getApelidoBanco();
    }

    @Override
    public long getId() { return id; }
    public String getNomeBanco() { return nomeBanco; }
    public String getApelidoBanco() { return apelidoBanco; }

    @Override
    public void setId(long id) { this.id = id; }

}
