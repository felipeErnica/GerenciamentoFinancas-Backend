package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.ConciliacaoDAO;
import com.santacarolina.financeiro.dto.ConciliacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/conciliacoes")
@SuppressWarnings("rawtypes")
public class ConciliacaoController {

    @Autowired
    private ConciliacaoDAO dao;

    @GetMapping
    public ResponseEntity<List<ConciliacaoDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ConciliacaoDTO> save(@RequestBody ConciliacaoDTO c) {
        try {
            dao.save(c);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<ConciliacaoDTO> list) {
        try {
            dao.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity deleteById(long id) {
        try {
            dao.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
