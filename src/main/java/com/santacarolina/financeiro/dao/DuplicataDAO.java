package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.DuplicataDTO;
import com.santacarolina.financeiro.enums.TipoPagamento;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DuplicataDAO implements DAO<DuplicataDTO> {

    private static final String UPDATE_QUERY = """
        UPDATE duplicatas
            SET documento_id = ?, num_dup = ?, data_vencimento = ?, tipo_pagamento = ?, valor = ?, boleto_caminho = ?, dado_id = ?, paga = ?
            WHERE id = ?
        """;
    private static final String INSERT_QUERY = """
            INSERT INTO duplicatas(documento_id, num_dup, data_vencimento, tipo_pagamento, valor, boleto_caminho, dado_id, paga) 
                VALUES(?,?,?,?,?,?,?,?);
            """;
    private static final String SELECT_QUERY = """
                SELECT dup.id, dup.num_dup, dup.tipo_pagamento, dup.data_vencimento, dup.valor, dup.dado_id, dup.documento_id, dup.boleto_caminho,
                    dup.paga,
                    c.nome as nome_contato,
                    p.conta_id,
                    cb.agencia, cb.numero_conta,
                    b.apelido_banco, b.nome_banco
                FROM duplicatas dup
                    LEFT JOIN documentos doc ON dup.documento_id = doc.id
                    LEFT JOIN contatos c ON c.id = doc.emissor_id
                    LEFT JOIN pastas_contabeis p ON p.id = doc.pasta_id
                    LEFT JOIN contas_bancarias cb ON cb.id = p.conta_id
                    LEFT JOIN bancos b ON b.id = cb.banco_id
            """;
    private CommonDAO<DuplicataDTO> commonDAO;

    @Autowired
    public DuplicataDAO(DataBaseConn conn) {
        this.commonDAO = new CommonDAO<>(this, conn);
    }

    public List<DuplicataDTO> findAll() throws SQLException {
        String query = SELECT_QUERY + "\nORDER BY dup.data_vencimento DESC;";
        return commonDAO.findList(query);
    }

    public List<DuplicataDTO> findByConciliacao(boolean isPayed) throws SQLException {
        String query = SELECT_QUERY  + "\n WHERE paga = " + isPayed +
                "\nORDER BY data_vencimento DESC;";
        return commonDAO.findList(query);
    }

    public void save(DuplicataDTO d) throws SQLException { commonDAO.save(d, UPDATE_QUERY, INSERT_QUERY); }
    public void saveAll(List<DuplicataDTO> list) throws SQLException { commonDAO.saveBatch(list, UPDATE_QUERY, INSERT_QUERY); }

    @Override
    public DuplicataDTO getDTO(ResultSet rs) throws SQLException {
        String conta;
        if (rs.getLong("conta_id") != 0) {
            String nomeBanco = rs.getString("apelido_banco") != null ? rs.getString("apelido_banco") : rs.getString("nome_banco");
            conta = nomeBanco  +
                    " AG: " + rs.getString("agencia") +
                    " CC: " + rs.getString("numero_conta");
        } else conta = null;

        return  new DuplicataDTO(
                rs.getLong("documento_id"),
                rs.getLong("dado_id"),
                rs.getLong("id"),
                rs.getInt("num_dup"),
                TipoPagamento.values()[rs.getInt("tipo_pagamento")],
                rs.getString("data_vencimento"),
                rs.getString("nome_contato"),
                rs.getString("boleto_caminho"),
                rs.getDouble("valor"),
                rs.getBoolean("paga"),
                conta
        );
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, DuplicataDTO d) throws SQLException {
        ps.setLong(1, d.getDocId());
        ps.setInt(2, d.getNumDup());
        ps.setDate(3, Date.valueOf(d.getDataVencimento()));
        ps.setInt(4, d.getTipoPagamento().getTipoPagamento());
        ps.setDouble(5, d.getValor());
        ps.setString(6, d.getBoletoCaminho());
        ps.setLong(7, d.getDadoId());
        ps.setBoolean(8, d.isPaga());
    }

    @Override
    public int getIdParameterIndex() { return 9; }

}
