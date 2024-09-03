package com.santacarolina.financeiro.models.entities;

import com.santacarolina.financeiro.models.enums.TipoPix;
import jakarta.persistence.*;

@Entity
@Table(name = "chaves_pix")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToOne
    @JoinColumn(name = "conta_id")
    private DadoBancario dadoBancario;
    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Contato contato;
    @Enumerated(value = EnumType.ORDINAL)
    private TipoPix tipoPix;
    private String chave;

    public ChavePix() {}

    public long getId() { return id; }
    public TipoPix getTipoPix() { return tipoPix; }
    public String getChave() { return chave; }
    public DadoBancario getDadoBancario() { return dadoBancario; }
    public Contato getContato() { return contato; }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChavePix{");
        sb.append("id=").append(id);
        sb.append(", dadoBancario=").append(dadoBancario);
        sb.append(", contato=").append(contato);
        sb.append(", tipoPix=").append(tipoPix);
        sb.append(", chave='").append(chave).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
