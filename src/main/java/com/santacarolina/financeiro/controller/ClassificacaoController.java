package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.ClassificacaoDAO;
import com.santacarolina.financeiro.dto.ClassificacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/classificacao")
public class ClassificacaoController {

    @Autowired
    private ClassificacaoDAO dao;

    @GetMapping
    private ResponseEntity<List<ClassificacaoDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<ClassificacaoDTO> findById (@PathVariable long id) {
        try {
            return dao.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/numeroIdentificacao={numeroIdentificacao}")
    private ResponseEntity<ClassificacaoDTO> getByNumero (@PathVariable long numeroIdentificacao) {
        try {
            return dao.getByNumero(numeroIdentificacao)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome={nome}")
    private ResponseEntity<ClassificacaoDTO> findByNome(@PathVariable String nome) {
        try {
            return dao.findByNome(nome)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
