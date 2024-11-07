package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.ContatoDAO;
import com.santacarolina.financeiro.dto.ContatoDTO;
import com.santacarolina.financeiro.entity.ContatoEntity;
import com.santacarolina.financeiro.repository.ContatoRepository;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/contatos")
@SuppressWarnings("rawtypes")
public class ContatoController {

    private final Logger logger = LogManager.getLogger();

    private ContatoDAO dao;
    private ContatoRepository repository;

    @Autowired
    public ContatoController(ContatoDAO dao, ContatoRepository repository) {
        this.dao = dao;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<ContatoDTO>> findAll(){
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cpf={cpf}")
    public ResponseEntity<ContatoDTO> findByCpf(@PathVariable String cpf) {
        try {
            return dao.findByCpf(cpf)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cnpj={cnpj}")
    public ResponseEntity<ContatoDTO> findByCnpj(@PathVariable String cnpj) {
        try {
            return dao.findByCnpj(cnpj)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/ie={ie}")
    public ResponseEntity<ContatoDTO> findByIe(@PathVariable String ie) {
        try {
            return dao.findByIe(ie)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> getContato(@PathVariable long id){
        try {
            return dao.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nome={nome}")
    public ResponseEntity<ContatoDTO> getByNome(@PathVariable String nome) {
        try {
            return dao.getByNome(nome)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity addContatos(@RequestBody List<ContatoEntity> contatos) {
        try {
            repository.saveAll(contatos);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContatoEntity> addContato(@RequestBody ContatoEntity contato) {
        try {
            return ResponseEntity.ok(repository.save(contato));
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContato(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
