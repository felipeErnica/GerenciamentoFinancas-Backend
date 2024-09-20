package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.enums.TipoMovimento;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class ConciliacaoDTO implements DataDAO {

    private long id;
    private TipoMovimento tipoMovimento;
    private Long duplicataId;
    private long extratoId;

    public ConciliacaoDTO(long id, TipoMovimento tipoMovimento, Long duplicataId, long extratoId) {
        this.id = id;
        this.tipoMovimento = tipoMovimento;
        this.duplicataId = duplicataId;
        this.extratoId = extratoId;
    }

    @Override
    public long getId() { return id; }
    public TipoMovimento getTipoMovimento() { return tipoMovimento; }
    public Long getDuplicataId() { return duplicataId; }
    public long getExtratoId() { return extratoId; }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ConciliacaoDTO{");
        sb.append("id=").append(id);
        sb.append(", tipoMovimento=").append(tipoMovimento);
        sb.append(", duplicataId=").append(duplicataId);
        sb.append(", extratoId=").append(extratoId);
        sb.append('}');
        return sb.toString();
    }
}
