package com.santacarolina.financeiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;
import com.santacarolina.financeiro.service.ProdutoDuplicataService;

/**
 * ProdutoDuplicataController
 */
@RestController
@RequestMapping("/homePage")
public class ProdutoDuplicataController {

    @Autowired
    private ProdutoDuplicataService service;
    
    @GetMapping
    public ResponseEntity<List<ProdutoDuplicataDTO>> findAll() {
        return ResponseEntity.ok(service.findTest());
    }

}
