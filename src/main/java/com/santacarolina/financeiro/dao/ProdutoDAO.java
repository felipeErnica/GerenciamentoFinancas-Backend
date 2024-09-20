package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.ProdutoDTO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProdutoDAO {

    @Autowired
    private DataBaseConn conn;
    private ResultSet rs;
    private PreparedStatement ps;

    public List<ProdutoDTO> findAll() throws SQLException {
        String query = """
                SELECT p.id, p.documento_id, p.classificacao_id, p.descricao, p.und, p.quantidade, p.valor_unit,
                    d.data_emissao,
                    c.nome as nome_contato,
                    pc.nome as nome_pasta,
                    cc.nome_classificacao
                FROM produtos p
                    LEFT JOIN documentos d ON d.id = p.documento_id
                    LEFT JOIN contatos c ON c.id = d.emissor_id
                    LEFT JOIN pastas_contabeis pc ON pc.id = d.pasta_id
                    LEFT JOIN classificacoes_contabeis cc ON cc.id = p.classificacao_id
                ORDER BY d.data_emissao DESC; 
            """;
        rs = conn.getResultSet(query);
        return populateList();
    }

    private List<ProdutoDTO> populateList() throws SQLException {
        List<ProdutoDTO> dtoList = new ArrayList<>();
        while (rs.next()) {
            ProdutoDTO p = getDTO();
            dtoList.add(p);
        }
        conn.closeConn();
        return dtoList;
    }

    private ProdutoDTO getDTO() throws SQLException {
        return new ProdutoDTO(
                rs.getLong("id"),
                rs.getLong("documento_id"),
                rs.getLong("classificacao_id"),
                rs.getString("descricao"),
                rs.getString("und"),
                rs.getDouble("quantidade"),
                rs.getDouble("valor_unit"),
                rs.getDate("data_emissao").toLocalDate(),
                rs.getString("nome_pasta"),
                rs.getString("nome_contato"),
                rs.getString("nome_classificacao")
        );
    }

}
