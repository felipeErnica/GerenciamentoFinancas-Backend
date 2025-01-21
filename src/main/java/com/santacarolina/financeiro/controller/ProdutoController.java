package com.santacarolina.financeiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dto.ProdutoDTO;
import com.santacarolina.financeiro.entity.ProdutoEntity;
import com.santacarolina.financeiro.service.ProdutoService;
import com.santacarolina.financeiro.util.LoggerMessage;

@RestController
@RequestMapping("/produtos")
@SuppressWarnings("rawtypes")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        LoggerMessage.generateMessage("PixController - findAll");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/documento={documentoId}")
    public ResponseEntity<List<ProdutoDTO>> findByDoc(@PathVariable long documentoId) {
        try {
            LoggerMessage.generateMessage("PixController - findByDoc");
            return ResponseEntity.ok(service.findByDoc(documentoId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ProdutoEntity entity) {
        try {
            LoggerMessage.generateMessage("PixController - save");
            service.save(entity);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<ProdutoEntity> list) {
        try {
            LoggerMessage.generateMessage("PixController - saveAll");
            service.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("PixController - deleteById");
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll(@RequestBody List<ProdutoEntity> list) {
        try {
            LoggerMessage.generateMessage("PixController - deleteAll");
            service.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
