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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.entity.DadoEntity;
import com.santacarolina.financeiro.service.DadoService;

@RestController
@RequestMapping("/contas")
@SuppressWarnings("rawtypes")
public class DadoController {

    @Autowired
    private DadoService service;

    @GetMapping
    public ResponseEntity<List<DadoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadoDTO> findById(@PathVariable long id) {
        try {
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/contato={contatoId}")
    public ResponseEntity<List<DadoDTO>> findByContato(@PathVariable long contatoId) {
        try {
            return ResponseEntity.ok(service.findByContato(contatoId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/info")
    public ResponseEntity<DadoDTO> getEqual(@RequestParam String agencia, String numeroConta, long bancoId) {
        try {
            return service.findEqual(agencia, numeroConta, bancoId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody DadoEntity d) {
        try {
            service.save(d);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll (@RequestBody List<DadoEntity> list) {
        try {
            service.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

