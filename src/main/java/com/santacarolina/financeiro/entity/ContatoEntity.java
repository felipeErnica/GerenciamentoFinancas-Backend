package com.santacarolina.financeiro.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * ContatoEntity
 */
@Entity
@Table(name = "contatos")
public class ContatoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String ie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contato", cascade = CascadeType.ALL)
    private List<DadoEntity> dadoList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contato",cascade = CascadeType.ALL)
    private List<PixEntity> pixList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contato", cascade = CascadeType.ALL)
    private List<DocumentoEntity> documentoList;

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getCnpj() { return cnpj; }
    public String getIe() { return ie; }
    public List<DadoEntity> getDadoList() { return dadoList; }
    public List<PixEntity> getPixList() { return pixList; }
    public List<DocumentoEntity> getDocumentoList() { return documentoList; }

}
