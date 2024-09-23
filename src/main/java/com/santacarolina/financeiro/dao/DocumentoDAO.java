package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentoDAO implements DAO<DocumentoDTO> {

    private static final String SELECT_QUERY = """
            SELECT id, doc_tipo, doc_numero, emissor_id, pasta_id, data_emissao, valor, caminho_documento, fluxo_caixa
            FROM documentos
            """;
    private static final String UPDATE_QUERY = """
            """;

    private CommonDAO<DocumentoDTO> commonDAO;

    @Autowired
    public DocumentoDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public List<DocumentoDTO> findAll() throws SQLException {
        String query = """
                    SELECT id, doc_tipo, doc_numero, emissor_id, pasta_id, data_emissao, valor, caminho_documento, fluxo_caixa
                    FROM documentos
                    ORDER BY data_emissao DESC; 
                """;
        List<DocumentoDTO> dtoList = new ArrayList<>();
        rs = conn.getResultSet(query);
        while(rs.next()) {
            DocumentoDTO dto = getDoc();
            dtoList.add(dto);
        }
        conn.closeConn();
        return dtoList;
    }


    public DocumentoDTO findById(long id) throws SQLException {
        String query = """
                    SELECT id, doc_tipo, doc_numero, emissor_id, pasta_id, data_emissao, valor, caminho_documento, fluxo_caixa
                    FROM documentos
                    WHERE id  = %d
                    ORDER BY data_emissao DESC;
                """.formatted(id);
        rs = conn.getResultSet(query);
        rs.next();
        DocumentoDTO dto = getDoc();
        conn.closeConn();
        return dto;
    }

    private DocumentoDTO getDoc() throws SQLException {
        return new DocumentoDTO(
                rs.getLong("id"),
                rs.getString("doc_numero"),
                TipoDocumento.values()[rs.getInt("doc_tipo")],
                rs.getLong("emissor_id"),
                rs.getString("caminho_documento"),
                rs.getLong("pasta_id"),
                rs.getDouble("valor"),
                rs.getString("data_emissao"),
                FluxoCaixa.values()[rs.getInt("fluxo_caixa")]
        );
    }

}
