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

import com.santacarolina.financeiro.dto.CategoriaDTO;
import com.santacarolina.financeiro.entity.CategoriaEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.service.CategoriaService;
import com.santacarolina.financeiro.service.UserService;

import jakarta.persistence.OptimisticLockException;

/**
 * CategoriaController
 */
@RestController
@RequestMapping("/categoria")
@SuppressWarnings("rawtypes")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable long id) {
        try {
            return service.findById(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
        
    @GetMapping("/nome={nome}")
    public ResponseEntity<CategoriaDTO> findByNome(@PathVariable String nome) {
        try {
            return service.findByNome(nome)
                .map(d -> ResponseEntity.ok(d))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/numero={numero}")
    public ResponseEntity<CategoriaDTO> findByNumero(@PathVariable Long numero) {
        try {
            return service.findByNumero(numero)
                .map(d -> ResponseEntity.ok(d))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() { 
        UserEntity userEntity = UserService.getLoggedUser();
        System.out.println("\n User ID: " + userEntity.getId() + "\n");
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CategoriaEntity t) {
        try {
            service.save(t);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll (@RequestBody List<CategoriaEntity> list) {
        try {
            service.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
