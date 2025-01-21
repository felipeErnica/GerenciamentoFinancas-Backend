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

import com.santacarolina.financeiro.dto.ExtratoDTO;
import com.santacarolina.financeiro.entity.ExtratoEntity;
import com.santacarolina.financeiro.service.ExtratoService;
import com.santacarolina.financeiro.util.LoggerMessage;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/extratos")
@SuppressWarnings("rawtypes")
public class ExtratoController {

    @Autowired
    private ExtratoService service;

    @GetMapping("/contaId={contaId}")
    public ResponseEntity<List<ExtratoDTO>> findByConta(@PathVariable long contaId) {
        try {
            LoggerMessage.generateMessage("ExtratoController - findByConta");
            return ResponseEntity.ok(service.findByContaId(contaId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/isConciliado={isConciliado}")
    public ResponseEntity<List<ExtratoDTO>> findByConciliacao(@PathVariable boolean isConciliado) {
        try {
            LoggerMessage.generateMessage("ExtratoController - findByConciliacao");
            return ResponseEntity.ok(service.findByConciliacao(isConciliado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ExtratoEntity d) {
        try {
            LoggerMessage.generateMessage("ExtratoController - save");
            service.save(d);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<ExtratoEntity> list)  {
        try {
            LoggerMessage.generateMessage("ExtratoController - saveAll");
            service.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("ExtratoController - deleteById");
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete-batch")
    public ResponseEntity deleteAll(@RequestBody List<ExtratoEntity> list)  {
        try {
            LoggerMessage.generateMessage("ExtratoController - deleteAll");
            service.deleteAllInBatch(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
