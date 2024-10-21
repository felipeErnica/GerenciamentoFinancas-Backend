package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.ContatoDAO;
import com.santacarolina.financeiro.dto.ContatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contatos")
@SuppressWarnings("rawtypes")
public class ContatoController {

    @Autowired
    private ContatoDAO dao;

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
    public ResponseEntity<ContatoDTO> getByNome(@RequestParam String nome) {
        try {
            return dao.getByNome(nome)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity addContatos(@RequestBody List<ContatoDTO> contatos) {
        try {
            dao.saveAll(contatos);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContatoDTO> addContato(@RequestBody ContatoDTO contato) {
        try {
            return ResponseEntity.ok(dao.save(contato));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContato(@PathVariable long id) {
        try {
            dao.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
