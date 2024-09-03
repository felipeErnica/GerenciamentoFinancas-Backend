package com.santacarolina.financeiro.models.entities;

import jakarta.persistence.*;

import java.util.List;

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
}
