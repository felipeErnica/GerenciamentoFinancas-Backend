package com.santacarolina.financeiro.entity;

import com.santacarolina.financeiro.enums.TipoMovimento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conciliacoes")
public class ConciliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "duplicata_id")
    private DuplicataEntity duplicata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "extrato_id")
    private ExtratoEntity extrato;

    @Enumerated(EnumType.ORDINAL)
    private TipoMovimento tipoMovimento;

    public long getId() { return id; }
    public DuplicataEntity getDuplicata() { return duplicata; }
    public ExtratoEntity getExtrato() { return extrato; }
    public TipoMovimento getTipoMovimento() { return tipoMovimento; }

}

