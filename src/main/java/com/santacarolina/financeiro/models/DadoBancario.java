package com.santacarolina.financeiro.models;

import com.santacarolina.financeiro.models.enums.TipoPix;
import jakarta.persistence.*;

@Entity
@Table(name = "dados_bancarios")
public class DadoBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String agencia;
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;
    private String numeroConta;
    private TipoPix tipoPix;
    private String chavePix;
    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public String getAgencia() {return agencia;}
    public Banco getBanco() {return banco;}
    public String getNumeroConta() {return numeroConta;}
    public TipoPix getTipoPix() {return tipoPix;}
    public String getChavePix() {return chavePix;}
    public Contato getContato() {return contato;}

}
