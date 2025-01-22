package com.santacarolina.financeiro.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.service.DocumentoService;

@RestController
@RequestMapping("/documentos")
@SuppressWarnings("rawtypes")
public class DocumentoController {

    @Autowired
    private DocumentoService service;

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> findById(@PathVariable long id) {
        try {
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
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
            return service.findEqual(contatoId, tipoDoc, dataEmissao, pastaId, valor)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/nota", params = {"contatoId", "numDoc"})
    public ResponseEntity<DocumentoDTO> findNotaEqual (@RequestParam long contatoId,
                                                       @RequestParam long numDoc) {
        try {
            return service.findNotaEqual(contatoId, numDoc)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody DocumentoEntity documento) {
        try {
            service.save(documento);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}") 
    private ResponseEntity deleteById(@PathVariable long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody DocumentoEntity documento) {
        try {
            service.delete(documento);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll(@RequestBody List<DocumentoEntity> list) {
        for (DocumentoEntity documento : list) {
            documento.getProdutoList().forEach(prod -> prod.setDocumento(documento));
            documento.getDuplicataList().forEach(dup -> dup.setDocumento(documento));
        }
        service.deleteBatch(list);
        return ResponseEntity.ok().build();
    }

}
