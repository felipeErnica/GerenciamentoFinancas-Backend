
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

@Entity
@Table(name = "bancos")
public class BancoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomeBanco;
    private String apelidoBanco;

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContaEntity> contaList;

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DadoEntity> dadoList;

    public long getId() { return id; }
    public String getNomeBanco() { return nomeBanco; }
    public String getApelidoBanco() { return apelidoBanco; }
    public List<ContaEntity> getContaList() { return contaList; }
    public List<DadoEntity> getDadoList() { return dadoList; }

}
