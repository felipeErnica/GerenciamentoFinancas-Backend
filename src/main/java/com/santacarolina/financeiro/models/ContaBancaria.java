package com.santacarolina.financeiro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "contas_bancarias")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String agencia;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "banco_id")
    private Banco banco;
    private String numeroConta;
    private String nomeConta;

    public long getId() { return id; }
    public String getAgencia() { return agencia; }
    public Banco getBanco() { return banco; }
    public String getNumeroConta() { return numeroConta; }
    public String getNomeConta() { return nomeConta; }

    public Builder builder() {
        return new Builder();
    }

    private ContaBancaria fromBuilder(Builder b) {
        return this;
    }

    public class Builder {

        private long id;
        private String agencia;
        private Banco banco;
        private String numeroConta;
        private String nomeConta;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setAgencia(String agencia) {
            this.agencia = agencia;
            return this;
        }

        public Builder setBanco(Banco banco) {
            this.banco = banco;
            return this;
        }

        public Builder setNumeroConta(String numeroConta) {
            this.numeroConta = numeroConta;
            return this;
        }

        public Builder setNomeConta(String nomeConta) {
            this.nomeConta = nomeConta;
            return this;
        }

        public ContaBancaria build() {
            return new ContaBancaria().fromBuilder(this);
        }

    }

}
