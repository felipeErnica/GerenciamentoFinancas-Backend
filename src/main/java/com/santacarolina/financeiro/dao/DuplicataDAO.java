package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.DuplicataDTO;
import com.santacarolina.financeiro.enums.FluxoCaixa;
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
import java.util.Optional;

@Component
public class DuplicataDAO implements DAO<DuplicataDTO> {

    private static final String UPDATE_QUERY = """
        UPDATE duplicatas
            SET documento_id = ?, num_dup = ?, data_vencimento = ?, tipo_pagamento = ?, valor = ?, boleto_caminho = ?, dado_id = ?, paga = ?, pix_id = ?
            WHERE id = ?
        """;
    private static final String INSERT_QUERY = """
            INSERT INTO duplicatas(documento_id, num_dup, data_vencimento, tipo_pagamento, valor, boleto_caminho, dado_id, paga, pix_id) 
                VALUES(?,?,?,?,?,?,?,?,?);
            """;
    private static final String SELECT_QUERY = """
                SELECT dup.id, dup.num_dup, dup.tipo_pagamento, dup.data_vencimento, dup.valor, dup.dado_id, dup.documento_id, dup.boleto_caminho,
                    doc.fluxo_caixa,
                    dup.paga, dup.pix_id,
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
    private static final String DELETE_QUERY = "DELETE FROM duplicatas WHERE id = ?";

    private CommonDAO<DuplicataDTO> commonDAO;

    @Autowired
    public DuplicataDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public List<DuplicataDTO> findAll() throws SQLException {
        String query = SELECT_QUERY + "ORDER BY dup.data_vencimento DESC;";
        return commonDAO.findList(query);
    }

    public List<DuplicataDTO> findPagas() throws SQLException {
        String query = SELECT_QUERY  + "WHERE dup.paga = true ORDER BY data_vencimento DESC;";
        return commonDAO.findList(query);
    }

    public List<DuplicataDTO> findNaoPagas() throws SQLException {
        String query = SELECT_QUERY  + "WHERE paga = false ORDER BY data_vencimento;";
        return commonDAO.findList(query);
    }

    public List<DuplicataDTO> findByDoc(long documentoId) throws SQLException {
        String query = SELECT_QUERY + "WHERE documento_id = " + documentoId + " ORDER BY data_vencimento";
        return commonDAO.findList(query);
    }

    public Optional<DuplicataDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + "WHERE id = " + id;
        return commonDAO.findOne(query);
    }

    public void save(DuplicataDTO d) throws SQLException { commonDAO.save(d, UPDATE_QUERY, INSERT_QUERY); }
    public void saveAll(List<DuplicataDTO> list) throws SQLException { commonDAO.saveBatch(list, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id);}
    public void deleteBatch(List<DuplicataDTO> list) throws SQLException { commonDAO.deleteBatch(DELETE_QUERY, list); }

    @Override
    public DuplicataDTO getDTO(ResultSet rs) throws SQLException {
        String conta;
        if (rs.getLong("conta_id") != 0) {
            String nomeBanco = rs.getString("apelido_banco") != null ? rs.getString("apelido_banco") : rs.getString("nome_banco");
            conta = nomeBanco  +
                    " AG: " + rs.getString("agencia") +
                    " CC: " + rs.getString("numero_conta");
        } else conta = null;

        return new DuplicataDTO(
                rs.getLong("documento_id"),
                (Long) rs.getObject("dado_id"),
                rs.getLong("id"),
                rs.getInt("num_dup"),
                TipoPagamento.values()[rs.getInt("tipo_pagamento")],
                rs.getDate("data_vencimento").toLocalDate(),
                rs.getString("nome_contato"),
                rs.getString("boleto_caminho"),
                rs.getDouble("valor"),
                rs.getBoolean("paga"),
                conta,
                (Long) rs.getObject("pix_id"),
                FluxoCaixa.fromValue(rs.getInt("fluxo_caixa")));
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, DuplicataDTO d) throws SQLException {
        ps.setLong(1, d.getDocId());
        ps.setInt(2, d.getNumDup());
        ps.setDate(3, Date.valueOf(d.getDataVencimento()));
        ps.setInt(4, d.getTipoPagamento().getValue());
        ps.setDouble(5, d.getValor());
        ps.setString(6, d.getBoletoCaminho());
        ps.setObject(7, d.getDadoId());
        ps.setBoolean(8, d.isPaga());
        ps.setObject(9, d.getPixId());
    }

    @Override
    public int getIdParameterIndex() { return 10; }

}
