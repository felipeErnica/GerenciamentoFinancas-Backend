package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.DocumentoDAO;
import com.santacarolina.financeiro.dao.DuplicataDAO;
import com.santacarolina.financeiro.dao.ProdutoDAO;
import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    private DocumentoDAO dao;
    private ProdutoDAO produtoDAO;
    private DuplicataDAO duplicataDAO;

    @Autowired
    public DocumentoController(DataBaseConn conn) {
        this.dao = new DocumentoDAO(conn);
        this.produtoDAO = new ProdutoDAO(conn);
        this.duplicataDAO = new DuplicataDAO(conn);
    }

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> findById(@PathVariable long id) {
        try {
            return dao.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/doc", params = {"contatoId", "tipoDoc", "dataEmissao", "pastaId", "valor"})
    public ResponseEntity<DocumentoDTO> findEqual(@RequestParam long contatoId,
                                                  @RequestParam TipoDocumento tipoDoc,
                                                  @RequestParam LocalDate dataEmissao,
                                                  @RequestParam long pastaId,
                                                  @RequestParam double valor) {
        try {
            return dao.findEqual(contatoId, tipoDoc, dataEmissao, pastaId, valor)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/nota", params = {"contatoId", "numDoc"})
    public ResponseEntity<DocumentoDTO> findNotaEqual (@RequestParam long contatoId,
                                                       @RequestParam long numDoc) {
        try {
            return dao.findNotaEqual(contatoId, numDoc)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<DocumentoDTO> save(@RequestBody DocumentoDTO dto) {
        try {
            dao.save(dto);
            System.out.println(dto);
            dto.getDuplicataList().forEach(d -> d.setDocId(dto.getId()));
            dto.getProdutoList().forEach(p -> p.setDocId(dto.getId()));
            duplicataDAO.saveAll(dto.getDuplicataList());
            produtoDAO.saveAll(dto.getProdutoList());
            return ResponseEntity.ok(dto);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
