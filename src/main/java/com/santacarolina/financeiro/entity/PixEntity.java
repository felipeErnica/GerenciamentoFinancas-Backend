package com.santacarolina.financeiro.entity;

import java.util.List;

import com.santacarolina.financeiro.enums.TipoPix;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "chaves_pix")
public class PixEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contato_id")
    private ContatoEntity contato;

    @OneToOne(mappedBy = "pix", fetch =  FetchType.LAZY)
    private DadoEntity dado;

    @Enumerated(EnumType.ORDINAL)
    private TipoPix tipoPix;
    private String chave;

    @OneToMany(mappedBy = "pix", cascade = CascadeType.ALL)
    private List<DuplicataEntity> duplicataList;

    public long getId() { return id; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getChave() { return chave; }
    public ContatoEntity getContato() { return contato; }
    public DadoEntity getDado() { return dado; }
    public List<DuplicataEntity> getDuplicataList() { return duplicataList; }

}
