package com.santacarolina.financeiro.dto;

import java.time.LocalDate;

import com.santacarolina.financeiro.enums.TipoMovimento;
import com.santacarolina.financeiro.interfaces.DataDAO;

public class ConciliacaoDTO implements DataDAO {

    private long id;
    private TipoMovimento tipoMovimento;
    private Long duplicataId;
    private LocalDate dataVencimento;
    private double valorDuplicata;
    private long pastaId;
    private String nomePasta;
    private long emissorId;
    private String nomeEmissor;
    private long contaId;
    private String contaBancaria;
    private long extratoId;
    private LocalDate dataExtrato;
    private String descExtrato;
    private String categoriaExtrato;
    private double valorExtrato;

    public ConciliacaoDTO(long id, TipoMovimento tipoMovimento, Long duplicataId, LocalDate dataVencimento,
             double valorDuplicata, long pastaId, String nomePasta, long emissorId, String nomeEmissor, long contaId,
            String contaBancaria, long extratoId, LocalDate dataExtrato, String descExtrato, String categoriaExtrato, double valorExtrato) {
        this.id = id;
        this.tipoMovimento = tipoMovimento;
        this.duplicataId = duplicataId;
        this.dataVencimento = dataVencimento;
        this.valorDuplicata = valorDuplicata;
        this.pastaId = pastaId;
        this.nomePasta = nomePasta;
        this.emissorId = emissorId;
        this.nomeEmissor = nomeEmissor;
        this.contaId = contaId;
        this.contaBancaria = contaBancaria;
        this.extratoId = extratoId;
        this.dataExtrato = dataExtrato;
        this.descExtrato = descExtrato;
        this.categoriaExtrato = categoriaExtrato;
        this.valorExtrato = valorExtrato;
    }

    @Override
    public long getId() { return id; }

    public TipoMovimento getTipoMovimento() { return tipoMovimento; }
    public Long getDuplicataId() { return duplicataId; }
    public long getExtratoId() { return extratoId; }
    public LocalDate getDataExtrato() { return dataExtrato; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public double getValorDuplicata() { return valorDuplicata; }
    public long getPastaId() { return pastaId; }
    public String getNomePasta() { return nomePasta; }
    public long getContaId() { return contaId; }
    public String getContaBancaria() { return contaBancaria; }
    public String getDescExtrato() { return descExtrato; }
    public String getCategoriaExtrato() { return categoriaExtrato; }
    public double getValorExtrato() { return valorExtrato; } 
    public long getEmissorId() { return emissorId; }
    public String getNomeEmissor() { return nomeEmissor; }

    @Override
    public void setId(long id) { this.id = id; }

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
