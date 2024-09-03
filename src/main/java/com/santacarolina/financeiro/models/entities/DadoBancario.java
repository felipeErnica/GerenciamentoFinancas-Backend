package com.santacarolina.financeiro.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "dados_bancarios")
public class DadoBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String agencia;
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;
    private String numeroConta;
    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;
    @OneToOne(mappedBy = "dadoBancario", fetch = FetchType.LAZY)
    private ChavePix chavePix;

    public long getId() {return id;}
    public String getAgencia() {return agencia;}
    public Banco getBanco() {return banco;}
    public String getNumeroConta() {return numeroConta;}
    public Contato getContato() {return contato;}
    public ChavePix getChavePix() { return chavePix; }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DadoBancario{");
        sb.append("id=").append(id);
        sb.append(", agencia='").append(agencia).append('\'');
        sb.append(", banco=").append(banco);
        sb.append(", numeroConta='").append(numeroConta).append('\'');
        sb.append(", contato=").append(contato);
        sb.append('}');
        return sb.toString();
    }
}
