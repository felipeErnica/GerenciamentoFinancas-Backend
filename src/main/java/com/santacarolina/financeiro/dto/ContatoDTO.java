package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.interfaces.DataDAO;

public class ContatoDTO implements DataDAO {

    private long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String ie;

    public ContatoDTO(long id, String nome, String cpf, String cnpj, String ie) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.ie = ie;
    }

    @Override
    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getCnpj() { return cnpj; }
    public String getIe() { return ie; }

}
