package com.santacarolina.financeiro.models.entities;

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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public long getId() {return id;}
    public String getNome() {return nome;}
    public String getCaminhoPasta() {return caminhoPasta;}
    public ContaBancaria getContaBancaria() {return contaBancaria;}
}
