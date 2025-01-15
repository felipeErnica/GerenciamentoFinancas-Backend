package com.santacarolina.financeiro.entity;

import java.util.List;

import com.santacarolina.financeiro.enums.FluxoCaixa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias_contabeis")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated
    private FluxoCaixa fluxoCaixa;
    
    private String numeroCategoria;
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade =  CascadeType.ALL)
    private List<ClassificacaoEntity> classicacaoList;

    public long getId() { return id; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public String getNumeroCategoria() { return numeroCategoria; }
    public String getNome() { return nome; }
    public List<ClassificacaoEntity> getClassicacaoList() { return classicacaoList; }
     
}

