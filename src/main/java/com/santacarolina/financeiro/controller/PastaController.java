package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.PastaDAO;
import com.santacarolina.financeiro.dto.PastaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/pastaContabil")
@SuppressWarnings("rawtypes")
public class PastaController {

    @Autowired
    private PastaDAO dao;

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
            return dao.findByNome(nome)
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
    public ResponseEntity save (@RequestBody PastaDTO pasta){
        try {
            dao.save(pasta);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePasta (@PathVariable long id){
        try {
            dao.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
