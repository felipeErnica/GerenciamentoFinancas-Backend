package com.santacarolina.financeiro.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "conciliacoes")
public class Conciliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "duplicata_id")
    private Duplicata duplicata;
    @ManyToOne
    @JoinColumn(name = "extrato_id")
    private Extrato extrato;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public Duplicata getDuplicata() {return duplicata;}
    public Extrato getExtrato() {return extrato;}
    public Usuario getUsuario() {return usuario;}

}
