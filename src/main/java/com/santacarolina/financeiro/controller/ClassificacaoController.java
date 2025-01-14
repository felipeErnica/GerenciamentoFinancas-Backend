package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.util.List;

import org.aspectj.internal.lang.annotation.ajcITD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.ClassificacaoDAO;
import com.santacarolina.financeiro.dto.ClassificacaoDTO;
import com.santacarolina.financeiro.entity.ClassificacaoEntity;
import com.santacarolina.financeiro.repository.ClassificacaoRepository;
import com.santacarolina.financeiro.service.ClassificacaoService;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/classificacao")
@SuppressWarnings("rawtypes")
public class ClassificacaoController {

    private ClassificacaoDAO dao;
    private ClassificacaoRepository repository;
    private ClassificacaoService service;

    @Autowired
    public ClassificacaoController(ClassificacaoDAO dao, ClassificacaoRepository repository, ClassificacaoService service) {
        this.dao = dao;
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    private ResponseEntity<List<ClassificacaoDTO>> findAll() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<ClassificacaoDTO> findById (@PathVariable long id) {
        try {
            return dao.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/numeroIdentificacao={numeroIdentificacao}")
    private ResponseEntity<ClassificacaoDTO> findByNumero (@PathVariable String numeroIdentificacao) {
        try {
            return dao.findByNumero(numeroIdentificacao)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome={nome}")
    private ResponseEntity<ClassificacaoDTO> findByNome(@PathVariable String nome) {
        try {
            return dao.findByNome(nome)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    private ResponseEntity save(@RequestBody ClassificacaoEntity dto) {
        try {
            repository.save(dto);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteById(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
