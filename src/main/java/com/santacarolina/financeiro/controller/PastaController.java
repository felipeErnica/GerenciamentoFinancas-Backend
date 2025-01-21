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

import com.santacarolina.financeiro.dto.PastaDTO;
import com.santacarolina.financeiro.entity.PastaEntity;
import com.santacarolina.financeiro.service.PastaService;
import com.santacarolina.financeiro.util.LoggerMessage;

@RestController
@RequestMapping("/pastaContabil")
@SuppressWarnings("rawtypes")
public class PastaController {

    @Autowired
    private PastaService service;

    @GetMapping
    public ResponseEntity<List<PastaDTO>> findAll(){
        try {
            LoggerMessage.generateMessage("PastaController - findAll");
            return ResponseEntity.ok(service.findAll());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome={nome}")
    public ResponseEntity<PastaDTO> findByNome(@PathVariable String nome) {
        try {
            LoggerMessage.generateMessage("PastaController - findByNome");
            return service.findByNome(nome)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PastaDTO> findById(@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("PastaController - findById");
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save (@RequestBody PastaEntity pasta){
        try {
            LoggerMessage.generateMessage("PastaController - save");
            service.save(pasta);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id){
        try {
            LoggerMessage.generateMessage("PastaController - deleteById");
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
