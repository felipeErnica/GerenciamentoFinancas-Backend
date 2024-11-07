package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.ConciliacaoDAO;
import com.santacarolina.financeiro.dto.ConciliacaoDTO;
import com.santacarolina.financeiro.entity.ConciliacaoEntity;
import com.santacarolina.financeiro.repository.ConciliacaoRepository;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/conciliacoes")
@SuppressWarnings("rawtypes")
public class ConciliacaoController {

    private ConciliacaoDAO dao;
    private ConciliacaoRepository repository;

    @Autowired
    public ConciliacaoController(ConciliacaoDAO dao, ConciliacaoRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ConciliacaoDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ConciliacaoDTO> save(@RequestBody ConciliacaoEntity c) {
        try {
            repository.save(c);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<ConciliacaoEntity> list) {
        try {
            repository.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity deleteById(long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
