package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.ProdutoDAO;
import com.santacarolina.financeiro.dto.ProdutoDTO;
import com.santacarolina.financeiro.entity.ProdutoEntity;
import com.santacarolina.financeiro.repository.ProdutoRepository;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/produtos")
@SuppressWarnings("rawtypes")
public class ProdutoController {

    private ProdutoDAO dao;
    private ProdutoRepository repository;

    @Autowired
    public ProdutoController(ProdutoDAO dao, ProdutoRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/documento={documentoId}")
    public ResponseEntity<List<ProdutoDTO>> findByDoc(@PathVariable long documentoId) {
        try {
            return ResponseEntity.ok(dao.findByDoc(documentoId));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity save(@RequestBody List<ProdutoEntity> list) {
        try {
            repository.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/batch")
    public ResponseEntity deleteAll(@RequestBody List<ProdutoEntity> list) {
        try {
            repository.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
