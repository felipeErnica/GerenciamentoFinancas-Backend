package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.text.Collator;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.DocumentoDAO;
import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.repository.DocumentoRepository;
import com.santacarolina.financeiro.util.DataBaseConn;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/documentos")
@SuppressWarnings("rawtypes")
public class DocumentoController {

    private DocumentoDAO dao;
    private DocumentoRepository repository;

    @Autowired
    public DocumentoController(DataBaseConn conn, DocumentoRepository repository) {
        this.dao = new DocumentoDAO(conn);
        this.repository = repository;
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
    public ResponseEntity save(@RequestBody DocumentoEntity dto) {
        try {
            repository.save(dto);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}") 
    private ResponseEntity deleteById(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody DocumentoEntity entity) {
        try {
            repository.delete(entity);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll(@RequestBody List<DocumentoEntity> list) {
        try {
            List<Long> idList = list.stream()
                .map(entity -> entity.getId())
                .collect(Collectors.toList());
            repository.deleteAllByIdInBatch(idList);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
