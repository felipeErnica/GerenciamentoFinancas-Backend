package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ConciliacaoEntity;
import com.santacarolina.financeiro.enums.TipoMovimento;

public class ConciliacaoDTO {

    private long id;
    private TipoMovimento tipoMovimento;
    private DuplicataDTO duplicata;
    private ExtratoDTO extrato;

    public ConciliacaoDTO(ConciliacaoEntity entity) {
        this.id = entity.getId();
        this.tipoMovimento = entity.getTipoMovimento();
        this.duplicata = entity.getDuplicata() != null ? new DuplicataDTO(entity.getDuplicata()) : null;
        this.extrato = entity.getExtrato() != null ? new ExtratoDTO(entity.getExtrato()) : null;
    }

    public long getId() { return id; }
    public TipoMovimento getTipoMovimento() { return tipoMovimento; }
    public DuplicataDTO getDuplicata() { return duplicata; }
    public ExtratoDTO getExtrato() { return extrato; }

}
