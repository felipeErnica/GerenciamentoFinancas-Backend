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
            return ResponseEntity.ok(service.findAll());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cpf={cpf}")
    public ResponseEntity<ContatoDTO> findByCpf(@PathVariable String cpf) {
        try {
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
            service.save(contato);
            return ResponseEntity.ok(new ContatoDTO(contato));
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContato(@PathVariable long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/delete-batch")
    public ResponseEntity deleteAll (@RequestBody List<ContatoEntity> list) {
        try {
            service.deleteAll(list);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException | IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
