package com.santacarolina.financeiro.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pastas_contabeis")
public class PastaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String nome;
    private String caminhoPasta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private ContaEntity conta;

    @OneToMany(mappedBy = "pasta", cascade = CascadeType.ALL)
    private List<DocumentoEntity> documentoList;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCaminhoPasta() {
        return caminhoPasta;
    }

    public ContaEntity getConta() {
        return conta;
    }

    public List<DocumentoEntity> getDocumentoList() {
        return documentoList;
    }

}
