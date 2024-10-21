package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.PixDAO;
import com.santacarolina.financeiro.dto.PixDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/chavesPix")
@SuppressWarnings("rawtypes")
public class PixController {

    @Autowired
    private PixDAO dao;

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

    @PostMapping
    public ResponseEntity save (@RequestBody PixDTO chavePix) {
        try {
            dao.save(chavePix);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            dao.delete(id);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
