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
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dto.ContatoDTO;
import com.santacarolina.financeiro.entity.ContatoEntity;
import com.santacarolina.financeiro.service.ContatoService;
import com.santacarolina.financeiro.util.LoggerMessage;

@RestController
@RequestMapping("/contatos")
@SuppressWarnings("rawtypes")
public class ContatoController {

    private final Logger logger = LogManager.getLogger();

    @Autowired
    private ContatoService service;

    @GetMapping
    public ResponseEntity<List<ContatoDTO>> findAll(){
        try {
            LoggerMessage.generateMessage("ContatoController - findAll");
            return ResponseEntity.ok(service.findAll());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cpf={cpf}")
    public ResponseEntity<ContatoDTO> findByCpf(@PathVariable String cpf) {
        try {
            LoggerMessage.generateMessage("ContatoController - findByCpf");
            return service.findByCpf(cpf)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cnpj={cnpj}")
    public ResponseEntity<ContatoDTO> findByCnpj(@PathVariable String cnpj) {
        try {
            LoggerMessage.generateMessage("ContatoController - findByCnpj");
            return service.findByCnpj(cnpj)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/ie={ie}")
    public ResponseEntity<ContatoDTO> findByIe(@PathVariable String ie) {
        try {
            LoggerMessage.generateMessage("ContatoController - findByIe");
            return service.findByIe(ie)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> getContato(@PathVariable long id){
        try {
            LoggerMessage.generateMessage("ContatoController - findById");
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome={nome}")
    public ResponseEntity<ContatoDTO> getByNome(@PathVariable String nome) {
        try {
            LoggerMessage.generateMessage("ContatoController - findByNome");
            return service.findByNome(nome)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity addContatos(@RequestBody List<ContatoEntity> contatos) {
        try {
            LoggerMessage.generateMessage("ContatoController - addContatos");
            service.saveAll(contatos);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContatoDTO> addContato(@RequestBody ContatoEntity contato) {
        try {
            LoggerMessage.generateMessage("ContatoController - addContato");
            service.save(contato);
            return ResponseEntity.ok(new ContatoDTO(contato));
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContato(@PathVariable long id) {
        try {
            LoggerMessage.generateMessage("ContatoController - deleteById");
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
