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

import com.santacarolina.financeiro.dto.DuplicataDTO;
import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.service.DuplicataService;
import com.santacarolina.financeiro.util.LoggerMessage;

@RestController
@RequestMapping("/duplicatas")
@SuppressWarnings("rawtypes")
public class DuplicataController {

    @Autowired
    private DuplicataService service;

    @GetMapping
    public ResponseEntity<List<DuplicataDTO>> findAll() {
        LoggerMessage.generateMessage("DuplicataController - findAll");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuplicataDTO> findById(@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("DuplicataController - findById");
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/pagas")
    public ResponseEntity<List<DuplicataDTO>> findPagas() {
        try {
            LoggerMessage.generateMessage("DuplicataController - findPagas");
            return ResponseEntity.ok(service.findPagas());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/naoPagas")
    public ResponseEntity<List<DuplicataDTO>> findNaoPagas() {
        try {
            LoggerMessage.generateMessage("DuplicataController - findNaoPagas");
            return ResponseEntity.ok(service.findNaoPagas());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/documento={documentoId}")
    public ResponseEntity<List<DuplicataDTO>> findByDoc (@PathVariable long documentoId) {
        try {
            LoggerMessage.generateMessage("DuplicataController - findByDoc");
            return ResponseEntity.ok(service.findByDoc(documentoId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody DuplicataEntity d) {
        try {
            LoggerMessage.generateMessage("DuplicataController - save");
            service.save(d);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<DuplicataEntity> list) {
        try {
            LoggerMessage.generateMessage("DuplicataController - saveAll");
            service.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("DuplicataController - deleteById");
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll(@RequestBody List<DuplicataEntity> list) {
        try {
            LoggerMessage.generateMessage("DuplicataController - deleteAll");
            service.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
