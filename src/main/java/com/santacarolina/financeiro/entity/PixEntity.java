package com.santacarolina.financeiro.entity;

import com.santacarolina.financeiro.enums.TipoPix;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chaves_pix")
public class PixEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //private long contatoId;
    //private String nomeContato;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private DadoEntity dado;

    @Enumerated(EnumType.ORDINAL)
    private TipoPix tipoPix;
    private String chave;

    public long getId() { return id; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getChave() { return chave; }

}
    
