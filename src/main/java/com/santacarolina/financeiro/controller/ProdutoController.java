package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.ProdutoDAO;
import com.santacarolina.financeiro.dto.ProdutoDTO;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoDAO dao;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/documento={documentoId}")
    public ResponseEntity<List<ProdutoDTO>> findByDoc (@PathVariable long documentoId) {
        try {
            return ResponseEntity.ok(dao.findByDoc(documentoId));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
