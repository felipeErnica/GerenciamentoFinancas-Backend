package com.santacarolina.financeiro.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dto.ContaDTO;
import com.santacarolina.financeiro.entity.ContaEntity;
import com.santacarolina.financeiro.service.ContaService;
import com.santacarolina.financeiro.util.LoggerMessage;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/contasAdm")
@SuppressWarnings("rawtypes")
public class ContaController {

    private Logger logger = LogManager.getLogger();

    @Autowired
    private ContaService service;

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        try {
            LoggerMessage.generateMessage("ContaController - findAll");
            return ResponseEntity.ok(service.findAll());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<ContaDTO> findEqual(@RequestParam String agencia,
                                                    @RequestParam String numeroConta,
                                                    @RequestParam long bancoId) {
        try {
            LoggerMessage.generateMessage("ContaController - findEqual");
            return service.findEqual(agencia, numeroConta, bancoId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("ContaController - findById");
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ContaEntity c) {
        try {
            LoggerMessage.generateMessage("ContaController - save");
            service.save(c);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("ContaController - deleteById");
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll(@RequestBody List<ContaEntity> list) {
        try {
            LoggerMessage.generateMessage("ContaController - deleteAll");
            service.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
