package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.PastaDAO;
import com.santacarolina.financeiro.dto.PastaDTO;
import com.santacarolina.financeiro.entity.PastaEntity;
import com.santacarolina.financeiro.repository.PastaRepository;

import jakarta.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/pastaContabil")
@SuppressWarnings("rawtypes")
public class PastaController {

    private PastaDAO dao;
    private PastaRepository repository;

    @Autowired
    public PastaController(PastaDAO dao, PastaRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<PastaDTO>> findAll(){
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome={nome}")
    public ResponseEntity<PastaDTO> findByNome(@PathVariable String nome) {
        try {
            return dao.findByNome(nome.replace("+", " "))
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PastaDTO> findById(@PathVariable long id) {
        try {
            return dao.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save (@RequestBody PastaEntity pasta){
        try {
            repository.save(pasta);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id){
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
