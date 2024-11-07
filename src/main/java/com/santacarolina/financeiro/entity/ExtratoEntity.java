package com.santacarolina.financeiro.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "extratos")
public class ExtratoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaEntity contaId;
    private LocalDate dataTransacao;
    private String categoriaExtrato;
    private String descricao;
    private double valor;
    private boolean conciliado;

    @OneToMany(mappedBy = "extrato", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ConciliacaoEntity> conciliacaoEntity;

    public long getId() { return id; }
    public ContaEntity getContaId() { return contaId; }
    public LocalDate getDataTransacao() { return dataTransacao; }
    public String getCategoriaExtrato() { return categoriaExtrato; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public boolean isConciliado() { return conciliado; }

}

