package com.santacarolina.financeiro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pastas_contabeis")
public class PastaContabil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nome;
    private String caminhoPasta;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaBancaria contaBancaria;

    public long getId() {return id;}
    public String getNome() {return nome;}
    public String getCaminhoPasta() {return caminhoPasta;}
    public ContaBancaria getContaBancaria() {return contaBancaria;}

    public Builder builder() {
        return new Builder();
    }

    private PastaContabil fromBuilder(Builder b) {
        id = b.id;
        nome = b.nome;
        caminhoPasta = b.caminhoPasta;
        contaBancaria = b.contaBancaria;
        return this;
    }

    public class Builder {

        private long id;
        private String nome;
        private String caminhoPasta;
        private ContaBancaria contaBancaria;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder setCaminhoPasta(String caminhoPasta) {
            this.caminhoPasta = caminhoPasta;
            return this;
        }

        public Builder setContaBancaria(ContaBancaria contaBancaria) {
            this.contaBancaria = contaBancaria;
            return this;
        }

        public PastaContabil build() {
            return new PastaContabil().fromBuilder(this);
        }

    }

}
