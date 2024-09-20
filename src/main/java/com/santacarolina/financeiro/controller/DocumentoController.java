package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.DocumentoDAO;
import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.models.DocumentoFiscal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoDAO dao;

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(dao.findById(id));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
