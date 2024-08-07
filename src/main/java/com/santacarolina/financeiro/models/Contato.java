package com.santacarolina.financeiro.models;

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

    public long getId() {return id;}
    public String getNome() {return nome;}
    public String getCpf() {return cpf;}
    public String getCnpj() {return cnpj;}
    public String getIe() {return ie;}

}
