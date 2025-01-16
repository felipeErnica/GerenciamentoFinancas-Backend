package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.PixEntity;
import com.santacarolina.financeiro.enums.TipoPix;

public class PixDTO {

    private long id;
    private long contatoId;
    private Long dadoId;
    private TipoPix tipoPix;
    private String chave;

    public PixDTO(PixEntity entity) {
        this.id = entity.getId();
        this.contatoId = entity.getContato() != null ? entity.getContato().getId() : 0;
        this.dadoId = entity.getDado() != null ? entity.getDado().getId() : null;
        this.tipoPix = entity.getTipoPix();
        this.chave = entity.getChave();
    }

    public long getId() { return id; }
    public long getContatoId() { return contatoId; }
    public Long getDadoId() { return dadoId; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getChave() { return chave; }

}
