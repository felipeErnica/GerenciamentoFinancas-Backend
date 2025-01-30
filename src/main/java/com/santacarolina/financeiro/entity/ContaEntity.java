package com.santacarolina.financeiro.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * ContaEntity
 */
@Entity
@Table(name = "contas_bancarias")
public class ContaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nomeConta;
    private String agencia;
    private String numeroConta;
    private String abreviacaoConta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_id")
    private BancoEntity banco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.REMOVE)
    private List<PastaEntity> pastaList;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.REMOVE)
    private List<ExtratoEntity> extratoList;

    public long getId() { return id; }
    public String getNomeConta() { return nomeConta; }
    public String getAgencia() { return agencia; }
    public String getNumeroConta() { return numeroConta; }
    public BancoEntity getBanco() { return banco; }
    public String getAbreviacaoConta() { return abreviacaoConta; }
    public List<PastaEntity> getPastaList() { return pastaList; }
    public List<ExtratoEntity> getExtratoList() { return extratoList; }

}
