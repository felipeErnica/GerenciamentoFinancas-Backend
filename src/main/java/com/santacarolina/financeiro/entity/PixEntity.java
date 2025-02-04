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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private DadoEntity dado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private TipoPix tipoPix;
    private String chave;

    @OneToMany(mappedBy = "pix", cascade = CascadeType.REMOVE)
    private List<DuplicataEntity> duplicataList;

    public long getId() { return id; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getChave() { return chave; }
    public ContatoEntity getContato() { return contato; }
    public DadoEntity getDado() { return dado; }
    public List<DuplicataEntity> getDuplicataList() { return duplicataList; }

    public void setUser(UserEntity user) { this.user = user; }
}
