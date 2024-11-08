package com.santacarolina.financeiro.dao;

import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.enums.FluxoCaixa;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.interfaces.DAO;
import com.santacarolina.financeiro.util.CommonDAO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class DocumentoDAO implements DAO<DocumentoDTO> {

    private static final String SELECT_QUERY = """
            SELECT d.id, d.tipo_doc, d.num_doc, d.emissor_id, d.pasta_id, d.data_emissao, d.valor, d.caminho_documento, d.fluxo_caixa,
                c.nome as nome_contato,
                p.nome as nome_pasta
            FROM documentos d
                LEFT JOIN contatos c ON c.id = d.emissor_id
                LEFT JOIN pastas_contabeis p ON p.id = d.pasta_id
            """;
    private static final String UPDATE_QUERY = """
            UPDATE public.documentos
            	SET tipo_doc=?, num_doc=?, emissor_id=?, pasta_id=?, data_emissao=?, valor=?, caminho_documento=?, fluxo_caixa=?
            	WHERE id = ?;
            """;
    private static final String INSERT_QUERY = """
            INSERT INTO public.documentos(tipo_doc, num_doc, emissor_id, pasta_id, data_emissao, valor, caminho_documento, fluxo_caixa)
            	VALUES (?, ?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String DELETE_QUERY = "DELETE FROM dcocumentos WHERE id = ?";

    private CommonDAO<DocumentoDTO> commonDAO;

    @Autowired
    public DocumentoDAO(DataBaseConn conn) { this.commonDAO = new CommonDAO<>(this, conn); }

    public List<DocumentoDTO> findAll() throws SQLException {
        String query = SELECT_QUERY + "ORDER BY data_emissao DESC;";
        return commonDAO.findList(query);
    }

    public Optional<DocumentoDTO> findById(long id) throws SQLException {
        String query = SELECT_QUERY + "WHERE d.id  = " + id;
        return commonDAO.findOne(query);
    }

    public Optional<DocumentoDTO> findEqual(long contatoId, TipoDocumento tipoDoc, LocalDate dataEmissao, long pastaId, double valor) throws SQLException {
        String query = SELECT_QUERY +
                "WHERE d.emissor_id = " + contatoId + " AND " +
                "d.tipo_doc = " + tipoDoc.getValue() + " AND " +
                "d.data_emissao = '" + dataEmissao + "' AND " +
                "d.pasta_id = " + pastaId + " AND " +
                "d.valor = " + valor;
        return commonDAO.findOne(query);
    }

    public Optional<DocumentoDTO> findNotaEqual(long contatoId, long numDoc) throws SQLException {
        String query = SELECT_QUERY + "WHERE d.emissor_id = " + contatoId + " AND d.num_doc = " + numDoc;
        return commonDAO.findOne(query);
    }

    public void save(DocumentoDTO dto) throws SQLException { commonDAO.save(dto, UPDATE_QUERY, INSERT_QUERY); }
    public void deleteById(long id) throws SQLException { commonDAO.deleteRecord(DELETE_QUERY, id); }

    @Override
    public DocumentoDTO getDTO(ResultSet rs) throws SQLException {
        return new DocumentoDTO(
                rs.getLong("id"),
                (Long) rs.getObject("num_doc"),
                TipoDocumento.fromValues(rs.getInt("tipo_doc")),
                rs.getLong("emissor_id"),
                rs.getString("caminho_documento"),
                rs.getLong("pasta_id"),
                rs.getDouble("valor"),
                rs.getDate("data_emissao").toLocalDate(),
                FluxoCaixa.fromValue(rs.getInt("fluxo_caixa")),
                rs.getString("nome_pasta"),
                rs.getString("nome_contato")
        );
    }

    @Override
    public void prepareValuesDTO(PreparedStatement ps, DocumentoDTO d) throws SQLException {
        ps.setInt(1, d.getTipoDoc().getValue());
        ps.setObject(2, d.getNumDoc());
        ps.setLong(3, d.getEmissorId());
        ps.setLong(4, d.getPastaId());
        ps.setDate(5, Date.valueOf(d.getDataEmissao()));
        ps.setDouble(6, d.getValor());
        ps.setString(7, d.getCaminho());
        ps.setInt(8, d.getFluxoCaixa().getValue());
    }

    @Override
    public int getIdParameterIndex() { return 9; }

}
