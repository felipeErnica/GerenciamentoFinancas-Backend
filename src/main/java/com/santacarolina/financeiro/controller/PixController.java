package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.PixDAO;
import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.entity.PixEntity;
import com.santacarolina.financeiro.repository.PixRepository;

import jakarta.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/chavesPix")
@SuppressWarnings("rawtypes")
public class PixController {

    private PixDAO dao;
    private PixRepository repository;

    @Autowired
    public PixController(PixDAO dao, PixRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<PixDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PixDTO> findById(@PathVariable long id) {
        try {
            return dao.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/chave={chave}")
    public ResponseEntity<PixDTO> findByChave (@PathVariable String chave) {
        try {
            return dao.findByChavePix(chave)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/contato={contatoId}")
    public ResponseEntity<List<PixDTO>> findByContato(@PathVariable long contatoId) {
        try {
            return ResponseEntity.ok(dao.findByContato(contatoId));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dadoId={dadoId}")
    public ResponseEntity<PixDTO> findByDado(@PathVariable long dadoId) {
        try {
            return dao.findByDado(dadoId)
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save (@RequestBody PixEntity chavePix) {
        try {
            repository.save(chavePix);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll (@RequestBody List<PixEntity> list) {
        try {
            repository.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
