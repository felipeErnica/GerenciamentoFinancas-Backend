package com.santacarolina.financeiro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "bancos")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomeBanco;
    private String apelidoBanco;

    public long getId() { return id; }
    public String getNomeBanco() { return nomeBanco; }
    public String getApelidoBanco() { return apelidoBanco; }

    public void setId(long id) { this.id = id; }
    public void setNomeBanco(String nomeBanco) { this.nomeBanco = nomeBanco; }
    public void setApelidoBanco(String apelidoBanco) { this.apelidoBanco = apelidoBanco; }

}
