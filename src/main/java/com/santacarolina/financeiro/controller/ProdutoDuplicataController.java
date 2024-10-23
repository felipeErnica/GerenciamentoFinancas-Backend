package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.ProdutoDuplicataDAO;
import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;

/**
 * ProdutoDuplicataController
 */
@RestController
@RequestMapping("/homePage")
public class ProdutoDuplicataController {

    @Autowired
    private ProdutoDuplicataDAO dao;
    
    @GetMapping
    public ResponseEntity<List<ProdutoDuplicataDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
