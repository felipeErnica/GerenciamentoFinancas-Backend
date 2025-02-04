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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "classificacoes_contabeis")
public class ClassificacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    @Enumerated
    private FluxoCaixa fluxoCaixa;
    
    private long numeroIdentificacao;
    private String nomeClassificacao;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "classificacao")
    private List<ProdutoEntity> listProdutos;

    public long getId() { return id; }
    public CategoriaEntity getCategoria() { return categoria; }
    public FluxoCaixa getFluxoCaixa() { return fluxoCaixa; }
    public long getNumeroIdentificacao() { return numeroIdentificacao; }
    public String getNomeClassificacao() { return nomeClassificacao; }

}

