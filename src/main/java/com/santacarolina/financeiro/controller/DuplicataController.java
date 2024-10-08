package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.DuplicataDAO;
import com.santacarolina.financeiro.dto.DuplicataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/duplicatas")
public class DuplicataController {

    @Autowired
    private DuplicataDAO dao;

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
    public ResponseEntity<DuplicataDTO> save(@RequestBody DuplicataDTO d) {
        try {
            dao.save(d);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<DuplicataDTO> list) {
        try {
            dao.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
