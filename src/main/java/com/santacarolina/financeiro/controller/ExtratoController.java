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
import com.santacarolina.financeiro.entity.ExtratoEntity;
import com.santacarolina.financeiro.repository.ExtratoRepository;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/extratos")
@SuppressWarnings("rawtypes")
public class ExtratoController {

    private ExtratoDAO dao;
    private ExtratoRepository repository;

    @Autowired
    public ExtratoController(ExtratoDAO dao, ExtratoRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

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
    public ResponseEntity save(@RequestBody ExtratoEntity d) {
        try {
            repository.save(d);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<ExtratoEntity> list)  {
        try {
            repository.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
