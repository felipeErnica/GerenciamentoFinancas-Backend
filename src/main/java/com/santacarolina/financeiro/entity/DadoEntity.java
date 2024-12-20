package com.santacarolina.financeiro.entity;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dados_bancarios")
public class DadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private BancoEntity banco;

    @OneToOne(orphanRemoval = true, mappedBy = "dado")
    private PixEntity pix;

    @ManyToOne
    @JoinColumn(name = "contato_id")
    private ContatoEntity contato;

    private String agencia;
    private String numeroConta;

    @OneToMany(mappedBy = "dado", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DuplicataEntity> duplicataList;

    public long getId() { return id; }
    public BancoEntity getBanco() { return banco; }
    public String getAgencia() { return agencia; }
    public String getNumeroConta() { return numeroConta; }
    public PixEntity getPix() { return pix; }
    public ContatoEntity getContato() { return contato; }
    public List<DuplicataEntity> getDuplicataList() { return duplicataList; }

}

