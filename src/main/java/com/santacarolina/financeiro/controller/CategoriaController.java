package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
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

import com.santacarolina.financeiro.dao.CategoriaDAO;
import com.santacarolina.financeiro.dao.ClassificacaoDAO;
import com.santacarolina.financeiro.dto.CategoriaDTO;
import com.santacarolina.financeiro.dto.ClassificacaoDTO;
import com.santacarolina.financeiro.util.DataBaseConn;

/**
 * CategoriaController
 */
@RestController
@RequestMapping("/categoria")
@SuppressWarnings("rawtypes")
public class CategoriaController {

    private CategoriaDAO dao;
    private ClassificacaoDAO classificacaoDAO;

    @Autowired
    public CategoriaController(DataBaseConn conn) {
        dao = new CategoriaDAO(conn);
        classificacaoDAO = new ClassificacaoDAO(conn);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable long id) {
        try {
            return dao.findById(id)
                .map(d -> ResponseEntity.ok(d))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
        
    @GetMapping("/nome={nome}")
    public ResponseEntity<CategoriaDTO> findByNome(@PathVariable String nome) {
        try {
            return dao.findByNome(nome)
                .map(d -> ResponseEntity.ok(d))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/numero={numero}")
    public ResponseEntity<CategoriaDTO> findByNumero(@PathVariable String numero) {
        try {
            return dao.findByNumero(numero)
                .map(d -> ResponseEntity.ok(d))
                .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CategoriaDTO t) {
        try {
            dao.save(t);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            dao.deleteById(id);
            List<ClassificacaoDTO> listClassificacao = classificacaoDAO.findByCategoria(id);
            classificacaoDAO.deleteBatch(listClassificacao);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
