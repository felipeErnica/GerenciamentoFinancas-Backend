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

import com.santacarolina.financeiro.dto.ConciliacaoDTO;
import com.santacarolina.financeiro.entity.ConciliacaoEntity;
import com.santacarolina.financeiro.service.ConciliacaoService;

@RestController
@RequestMapping("/conciliacoes")
@SuppressWarnings("rawtypes")
public class ConciliacaoController {

    @Autowired
    private ConciliacaoService service;

    @GetMapping
    public ResponseEntity<List<ConciliacaoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/extrato/{extratoId}")
    public ResponseEntity<List<ConciliacaoDTO>> findByExtrato(@PathVariable long extratoId) {
        return ResponseEntity.ok(service.findByExtrato(extratoId));
    }

    @GetMapping("/duplicata/{duplicataId}")
    public ResponseEntity<List<ConciliacaoDTO>> findByDuplicata(@PathVariable long duplicataId) {
        return ResponseEntity.ok(service.findByDuplicata(duplicataId));
    }

    @PostMapping
    public ResponseEntity<ConciliacaoDTO> save(@RequestBody ConciliacaoEntity c) {
        try {
            service.save(c);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<ConciliacaoEntity> list) {
        try {
            service.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity deleteById(long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll (@RequestBody List<ConciliacaoEntity> list) {
        try {
            service.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
