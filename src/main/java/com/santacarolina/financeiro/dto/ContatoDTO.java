package com.santacarolina.financeiro.dto;

import com.santacarolina.financeiro.entity.ContatoEntity;

public class ContatoDTO {

    private long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String ie;

    public ContatoDTO(ContatoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.cnpj = entity.getCnpj();
        this.ie = entity.getIe();
    }

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getCnpj() { return cnpj; }
    public String getIe() { return ie; }

}
