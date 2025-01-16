package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ConciliacaoEntity;
import com.santacarolina.financeiro.enums.TipoMovimento;

public class ConciliacaoDTO {

    private long id;
    private TipoMovimento tipoMovimento;
    private Long duplicataId;
    private long extratoId;

    public ConciliacaoDTO(ConciliacaoEntity entity) {
        this.id = entity.getId();
        this.tipoMovimento = entity.getTipoMovimento();
        this.duplicataId = entity.getDuplicata() != null ? entity.getDuplicata().getId() : null;
        this.extratoId = entity.getExtrato() != null ? entity.getExtrato().getId() : null;
    }

    public long getId() { return id; }
    public TipoMovimento getTipoMovimento() { return tipoMovimento; }
    public Long getDuplicataId() { return duplicataId; }
    public long getExtratoId() { return extratoId; }

}
