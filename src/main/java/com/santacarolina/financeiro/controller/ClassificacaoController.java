package com.santacarolina.financeiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dto.ClassificacaoDTO;
import com.santacarolina.financeiro.entity.ClassificacaoEntity;
import com.santacarolina.financeiro.service.ClassificacaoService;
import com.santacarolina.financeiro.util.LoggerMessage;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/classificacao")
@SuppressWarnings("rawtypes")
public class ClassificacaoController {

    @Autowired
    private ClassificacaoService service;

    @GetMapping
    private ResponseEntity<List<ClassificacaoDTO>> findAll() {
        try {
            LoggerMessage.generateMessage("ClassificacaoController - findAll");
            return ResponseEntity.ok(service.findAll());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<ClassificacaoDTO> findById (@PathVariable long id) {
        LoggerMessage.generateMessage("ClassificacaoController - findById");
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/numeroIdentificacao={numeroIdentificacao}")
    private ResponseEntity<ClassificacaoDTO> findByNumero (@PathVariable String numeroIdentificacao) {
        try {
            LoggerMessage.generateMessage("ClassificacaoController - findByNumero");
            return service.findByNumero(numeroIdentificacao)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome={nome}")
    private ResponseEntity<ClassificacaoDTO> findByNome(@PathVariable String nome) {
        try {
            LoggerMessage.generateMessage("ClassificacaoController - findByNome");
            return service.findByNome(nome)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    private ResponseEntity save(@RequestBody ClassificacaoEntity dto) {
        try {
            LoggerMessage.generateMessage("ClassificacaoController - save");
            service.save(dto);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@PathVariable long id) {
        LoggerMessage.generateMessage("ClassificacaoController - deleteById");
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
