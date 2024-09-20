package com.santacarolina.financeiro.models;

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
    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;
    @OneToOne(mappedBy = "dadoBancario", cascade = CascadeType.ALL)
    private ChavePix chavePix;

    public long getId() {return id;}
    public String getAgencia() {return agencia;}
    public Banco getBanco() {return banco;}
    public String getNumeroConta() {return numeroConta;}
    public Contato getContato() {return contato;}
    public ChavePix getChavePix() { return chavePix; }

    public void setId(long id) { this.id = id; }
    public void setAgencia(String agencia) { this.agencia = agencia; }
    public void setBanco(Banco banco) { this.banco = banco; }
    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }
    public void setContato(Contato contato) { this.contato = contato; }
    public void setChavePix(ChavePix chavePix) { this.chavePix = chavePix; }

    public void addChavePix(ChavePix c) {
        this.chavePix = c;
        c.setDadoBancario(this);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DadoBancario{");
        sb.append("id=").append(id);
        sb.append(", agencia='").append(agencia).append('\'');
        sb.append(", banco=").append(banco);
        sb.append(", numeroConta='").append(numeroConta).append('\'');
        sb.append(", contato=").append(contato);
        sb.append('}');
        return sb.toString();
    }


    private DadoBancario fromBuilder(Builder builder) {
        id = builder.id;
        agencia = builder.agencia;
        banco = builder.banco;
        numeroConta = builder.numeroConta;
        contato = builder.contato;
        chavePix = builder.chavePix;
        return this;
    }

    public class Builder {

        private long id;
        private String agencia;
        private Banco banco;
        private String numeroConta;
        private Contato contato;
        private ChavePix chavePix;

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

        public Builder setContato(Contato contato) {
            this.contato = contato;
            return this;
        }

        public Builder setChavePix(ChavePix chavePix) {
            this.chavePix = chavePix;
            return this;
        }

        public DadoBancario build() {
            return new DadoBancario().fromBuilder(this);
        }

    }

}
