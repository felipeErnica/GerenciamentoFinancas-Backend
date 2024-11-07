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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santacarolina.financeiro.dao.DadoDAO;
import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.entity.DadoEntity;
import com.santacarolina.financeiro.repository.DadoRepository;
import com.santacarolina.financeiro.util.DataBaseConn;

import jakarta.persistence.OptimisticLockException;

@RestController
@RequestMapping("/contas")
@SuppressWarnings("rawtypes")
public class DadoController {

    private DadoDAO dadoDAO;
    private DadoRepository repository;

    @Autowired
    public DadoController(DataBaseConn conn, DadoRepository repository) {
        this.dadoDAO = new DadoDAO(conn);
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<DadoDTO>> findAll() {
        try {
            return ResponseEntity.ok(dadoDAO.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadoDTO> findById(@PathVariable long id) {
        try {
            return dadoDAO.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/contato={contatoId}")
    public ResponseEntity<List<DadoDTO>> findByContato(@PathVariable long contatoId) {
        try {
            return ResponseEntity.ok(dadoDAO.getPixByContato(contatoId));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/info")
    public ResponseEntity<DadoDTO> getEqual(@RequestParam String agencia, String numeroConta, long bancoId) {
        try {
            return dadoDAO.getEqual(agencia, numeroConta, bancoId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody DadoEntity d) {
        try {
            repository.save(d);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

