package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.ConciliacaoDTO;
import com.santacarolina.financeiro.enums.TipoMovimento;
import com.santacarolina.financeiro.enums.TipoPagamento;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ConciliacaoDAO implements DAO<ConciliacaoDTO> {
    
    private static final String SELECT_QUERY = """
        SELECT conc.id, conc.tipo_movimento, conc.duplicata_id, conc.extrato_id,
            dup.data_vencimento, dup.tipo_pagamento, dup.valor as valor_duplicata,
            pasta.nome as nome_pasta,
            contatos.id as emissor_id, contatos.nome as nome_emissor,
            ex.data_transacao, ex.conta_id, ex.categoria_extrato, ex.descricao, ex.valor as valor_extrato,
            conta.abreviacao_conta
        FROM conciliacoes as conc
            LEFT JOIN duplicatas as dup ON dup.id = conc.duplicata_id
            LEFT JOIN extratos as ex ON ex.id = conc.extrato_id
            LEFT JOIN documentos as doc ON doc.id = dup.documento_id
            LEFT JOIN contatos ON contatos.id = doc.emissor_id
            LEFT JOIN pastas_contabeis as pasta ON pasta.id = doc.pasta_id
            LEFT JOIN contas_bancarias as conta ON conta.id = ex.conta_id
    """;

    private static final String UPDATE_QUERY = """
            UPDATE conciliacoes 
            SET tipo_movimento = ?, duplicata_id = ?, extrato_id = ? 
            WHERE id = ?
        """;
    private static final String INSERT_QUERY = """
            INSERT INTO conciliacoes(tipo_movimento, duplicata_id, extrato_id) 
            VALUES (?,?,?)
        """;

    private static final String DELETE_QUERY = "DELETE FROM conciliacoes WHERE id = ?";

    private CommonDAO<ConciliacaoDTO> commonDAO;

    @Autowired
    public ConciliacaoDAO(DataBaseConn conn) {
        this.commonDAO = new CommonDAO<>(this, conn);
    }

    public List<ConciliacaoDTO> findAll() throws SQLException {
        String query = SELECT_QUERY + " ORDER BY data_transacao DESC";
        return commonDAO.findList(query); 
    }

    public void save(ConciliacaoDTO c) throws SQLException { commonDAO.save(c, UPDATE_QUERY, INSERT_QUERY); }
    public void saveAll(List<ConciliacaoDTO> list) throws SQLException { commonDAO.saveBatch(list, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }

    @Override
    public ConciliacaoDTO getDTO(ResultSet rs) throws SQLException {
        return new ConciliacaoDTO(
            rs.getLong("id"), 
            TipoMovimento.fromValue(rs.getInt("tipo_pagamento")),
            (Long) rs.getLong("duplicata_id"),
            rs.getDate("data_vencimento").toLocalDate(),
            rs.getObject("tipo_pagamento") != null ? TipoPagamento.fromValue(rs.getInt("tipo_pagamento")) : null,
            rs.getDouble("valor_duplicata"),
            rs.getLong("pasta_id"),
            rs.getString("nome_pasta"),
            rs.getLong("emissor_id"),
            rs.getString("nome_emissor"),
            rs.getLong("conta_id"),
            rs.getString("abreviacao_conta"),
            rs.getLong("extrato_id"),
            rs.getDate("data_transacao").toLocalDate(),
            rs.getString("descricao"),
            rs.getString("categori_extrato"),
            rs.getDouble("valor_extrato"));
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, ConciliacaoDTO dto) throws SQLException {
        ps.setInt(1, dto.getTipoMovimento().getValue());
        ps.setObject(2, dto.getDuplicataId());
        ps.setLong(3, dto.getExtratoId());
    }

    @Override
    public int getIdParameterIndex() { return 4; }

}
