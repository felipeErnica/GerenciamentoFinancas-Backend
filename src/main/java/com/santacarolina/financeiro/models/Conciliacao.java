package com.santacarolina.financeiro.models;

public class Conciliacao {

    private long id;
    private Extrato extrato;
    private Duplicata duplicata;

    public long getId() { return id; }
    public Extrato getExtrato() { return extrato; }
    public Duplicata getDuplicata() { return duplicata; }

    public void setId(long id) { this.id = id; }
    public void setExtrato(Extrato extrato) { this.extrato = extrato; }
    public void setDuplicata(Duplicata duplicata) { this.duplicata = duplicata; }

}
