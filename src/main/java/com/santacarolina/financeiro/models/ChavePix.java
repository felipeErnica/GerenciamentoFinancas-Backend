package com.santacarolina.financeiro.models;

import com.santacarolina.financeiro.enums.TipoPix;
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

    public void setId(long id) { this.id = id; }
    public void setDadoBancario(DadoBancario dadoBancario) { this.dadoBancario = dadoBancario; }
    public void setContato(Contato contato) { this.contato = contato; }
    public void setTipoPix(TipoPix tipoPix) { this.tipoPix = tipoPix; }
    public void setChave(String chave) { this.chave = chave; }

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
