package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.entity.PixEntity;
import com.santacarolina.financeiro.service.PixService;
import com.santacarolina.financeiro.util.LoggerMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chavesPix")
@SuppressWarnings("rawtypes")
public class PixController {

    @Autowired
    private PixService service;

    @GetMapping
    public ResponseEntity<List<PixDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PixDTO> findById(@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("PixController - findById");
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/chave={chave}")
    public ResponseEntity<PixDTO> findByChave (@PathVariable String chave) {
        try {
            LoggerMessage.generateMessage("PixController - findByChave");
            return service.findByChavePix(chave)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/contato={contatoId}")
    public ResponseEntity<List<PixDTO>> findByContato(@PathVariable long contatoId) {
        try {
            LoggerMessage.generateMessage("PixController - findByContato");
            return ResponseEntity.ok(service.findByContato(contatoId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dadoId={dadoId}")
    public ResponseEntity<PixDTO> findByDado(@PathVariable long dadoId) {
        try {
            LoggerMessage.generateMessage("PixController - findByDado");
            return service.findByDado(dadoId)
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save (@RequestBody PixEntity chavePix) {
        try {
            LoggerMessage.generateMessage("PixController - save");
            service.save(chavePix);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("PixController - deleteById");
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
