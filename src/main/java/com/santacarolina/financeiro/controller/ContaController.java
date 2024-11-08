package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.ContaDAO;
import com.santacarolina.financeiro.dto.ContaDTO;
import com.santacarolina.financeiro.entity.ContaEntity;
import com.santacarolina.financeiro.repository.ContaRepository;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/contasAdm")
@SuppressWarnings("rawtypes")
public class ContaController {

    private Logger logger = LogManager.getLogger();

    private ContaDAO dao;
    private ContaRepository repository;

    @Autowired
    public ContaController(ContaDAO dao, ContaRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<ContaDTO> findEqual(@RequestParam String agencia,
                                                    @RequestParam String numeroConta,
                                                    @RequestParam long bancoId) {
        try {
            return dao.findEqual(agencia, numeroConta, bancoId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable long id) {
        try {
            return dao.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ContaEntity c) {
        try {
            repository.save(c);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll(@RequestBody List<ContaEntity> list) {
        try {
            repository.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
