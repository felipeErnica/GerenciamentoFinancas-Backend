package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.DuplicataDAO;
import com.santacarolina.financeiro.dto.DuplicataDTO;
import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.repository.DuplicataRepository;

import jakarta.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/duplicatas")
@SuppressWarnings("rawtypes")
public class DuplicataController {

    private DuplicataDAO dao;
    private DuplicataRepository repository;

    @Autowired
    public DuplicataController(DuplicataDAO dao, DuplicataRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<DuplicataDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuplicataDTO> findById(@PathVariable long id) {
        try {
            return dao.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/pagas")
    public ResponseEntity<List<DuplicataDTO>> findPagas() {
        try {
            return ResponseEntity.ok(dao.findPagas());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/naoPagas")
    public ResponseEntity<List<DuplicataDTO>> findNaoPagas() {
        try {
            return ResponseEntity.ok(dao.findNaoPagas());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/documento={documentoId}")
    public ResponseEntity<List<DuplicataDTO>> findByDoc (@PathVariable long documentoId) {
        try {
            return ResponseEntity.ok(dao.findByDoc(documentoId));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody DuplicataEntity d) {
        try {
            repository.save(d);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<DuplicataEntity> list) {
        try {
            repository.saveAll(list);
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

}
