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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dados_bancarios")
public class DadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_id")
    private BancoEntity banco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contato_id")
    private ContatoEntity contato;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pix_id")
    private PixEntity pix;

    private String agencia;
    private String numeroConta;

    @OneToMany(mappedBy = "dado", cascade = CascadeType.ALL)
    private List<DuplicataEntity> duplicataList;

    public long getId() { return id; }
    public BancoEntity getBanco() { return banco; }
    public String getAgencia() { return agencia; }
    public String getNumeroConta() { return numeroConta; }
    public ContatoEntity getContato() { return contato; }
    public PixEntity getPix() { return pix; }
    public List<DuplicataEntity> getDuplicataList() { return duplicataList; }

}

