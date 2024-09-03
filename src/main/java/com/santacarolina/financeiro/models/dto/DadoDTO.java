package com.santacarolina.financeiro.models.dto;

import com.santacarolina.financeiro.models.entities.Banco;
import com.santacarolina.financeiro.models.entities.ChavePix;
import com.santacarolina.financeiro.models.entities.Contato;
import com.santacarolina.financeiro.models.entities.DadoBancario;
import com.santacarolina.financeiro.models.enums.TipoPix;

public class DadoDTO {

    private long id;
    private Contato contato;
    private String agencia;
    private Banco banco;
    private String numeroConta;
    private ChavePixDado chavePix;

    public DadoDTO() {
    }

    public DadoDTO(DadoBancario d) {
        this.id = d.getId();
        this.contato = d.getContato();
        this.agencia = d.getAgencia();
        this.banco = d.getBanco();
        this.numeroConta = d.getNumeroConta();
        if (d.getChavePix() != null) this.chavePix = new ChavePixDado(d.getChavePix());
        else this.chavePix = null;
    }

    public long getId() { return id; }
    public Contato getContato() { return contato; }
    public String getAgencia() { return agencia; }
    public Banco getBanco() { return banco; }
    public String getNumeroConta() { return numeroConta; }
    public ChavePixDado getChavePix() { return chavePix; }

    private class ChavePixDado {

        private long id;
        private TipoPix tipoPix;
        private String chave;
        private Contato contato;

        public ChavePixDado() {
        }

        public ChavePixDado(ChavePix c) {
            this.id = c.getId();
            this.tipoPix = c.getTipoPix();
            this.chave = c.getChave();
            this.contato = c.getContato();
        }

        public long getId() { return id; }
        public TipoPix getTipoPix() { return tipoPix; }
        public String getChave() { return chave; }
        public Contato getContato() { return contato; }

    }

}
