package com.santacarolina.financeiro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String ie;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getCnpj() { return cnpj; }
    public String getIe() { return ie; }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contato{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", cpf='").append(cpf).append('\'');
        sb.append(", cnpj='").append(cnpj).append('\'');
        sb.append(", ie='").append(ie).append('\'');
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }

    public Builder builder() { return new Builder(); }

    private Contato fromBuilder(Builder builder) {
        id = builder.id;
        nome = builder.nome;
        cpf = builder.cpf;
        cnpj = builder.cnpj;
        ie = builder.ie;
        return this;
    }

    public class Builder {

        private long id;
        private String nome;
        private String cpf;
        private String cnpj;
        private String ie;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder setCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Builder setIe(String ie) {
            this.ie = ie;
            return this;
        }

        public Contato build() {
            return new Contato().fromBuilder(this);
        }

    }

}
