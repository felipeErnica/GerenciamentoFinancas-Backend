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

import com.santacarolina.financeiro.dao.CategoriaDAO;
import com.santacarolina.financeiro.dto.CategoriaDTO;
import com.santacarolina.financeiro.entity.CategoriaEntity;
import com.santacarolina.financeiro.repository.CategoriaRepository;
import com.santacarolina.financeiro.service.CategoriaService;

import jakarta.persistence.OptimisticLockException;

/**
 * CategoriaController
 */
@RestController
@RequestMapping("/categoria")
@SuppressWarnings("rawtypes")
public class CategoriaController {

    private CategoriaDAO dao;
    private CategoriaRepository repository;
    private CategoriaService service;

    @Autowired
    public CategoriaController(CategoriaDAO dao, CategoriaRepository repository, CategoriaService service) {
        this.dao = dao;
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable long id) {
        try {
            return service.findById(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
        
    @GetMapping("/nome={nome}")
    public ResponseEntity<CategoriaDTO> findByNome(@PathVariable String nome) {
        try {
            return dao.findByNome(nome)
                .map(d -> ResponseEntity.ok(d))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/numero={numero}")
    public ResponseEntity<CategoriaDTO> findByNumero(@PathVariable String numero) {
        try {
            return dao.findByNumero(numero)
                .map(d -> ResponseEntity.ok(d))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CategoriaEntity t) {
        try {
            repository.save(t);
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

}
