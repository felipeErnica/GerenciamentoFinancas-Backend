package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.ExtratoDAO;
import com.santacarolina.financeiro.dto.ExtratoDTO;

@RestController
@RequestMapping("/extratos")
@SuppressWarnings("rawtypes")
public class ExtratoController {

    @Autowired
    private ExtratoDAO dao;

    @GetMapping("/contaId={contaId}")
    public ResponseEntity<List<ExtratoDTO>> findByConta(@PathVariable long contaId) {
        try {
            return ResponseEntity.ok(dao.findByContaId(contaId));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/isConciliado={isConciliado}")
    public ResponseEntity<List<ExtratoDTO>> findByConciliacao(@PathVariable boolean isConciliado) {
        try {
            return ResponseEntity.ok(dao.findByConciliacao(isConciliado));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ExtratoDTO> save(@RequestBody ExtratoDTO d) {
        try {
            dao.save(d);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<ExtratoDTO> list)  {
        try {
            dao.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
