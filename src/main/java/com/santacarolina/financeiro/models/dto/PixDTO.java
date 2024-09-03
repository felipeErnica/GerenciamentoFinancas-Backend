package com.santacarolina.financeiro.models.dto;

import com.santacarolina.financeiro.models.entities.Banco;
import com.santacarolina.financeiro.models.entities.ChavePix;
import com.santacarolina.financeiro.models.entities.Contato;
import com.santacarolina.financeiro.models.entities.DadoBancario;
import com.santacarolina.financeiro.models.enums.TipoPix;

public class PixDTO {

    private long id;
    private Contato contato;
    private DadoPix dadoBancario;
    private TipoPix tipoPix;
    private String chave;

    public PixDTO() {
    }

    public PixDTO(ChavePix c) {
        this.id = c.getId();
        this.contato = c.getContato();
        this.dadoBancario = c.getDadoBancario() != null ? new DadoPix(c.getDadoBancario()) : null;
        this.tipoPix = c.getTipoPix();
        this.chave = c.getChave();
    }

    public long getId() { return id; }
    public DadoPix getDadoBancario() { return dadoBancario; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getChave() { return chave; }
    public Contato getContato() { return contato; }

    private class DadoPix {

        private long id;
        private String agencia;
        private Banco banco;
        private String numeroConta;

        public DadoPix() {
        }

        public DadoPix(DadoBancario d) {
            this.id = d.getId();
            this.agencia = d.getAgencia();
            this.banco = d.getBanco();
            this.numeroConta = d.getNumeroConta();
        }

        public long getId() { return id; }
        public String getAgencia() { return agencia; }
        public Banco getBanco() { return banco; }
        public String getNumeroConta() { return numeroConta; }

    }

}
