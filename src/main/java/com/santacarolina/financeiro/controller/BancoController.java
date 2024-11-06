package com.santacarolina.financeiro.controller;

import java.sql.SQLException;
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

import com.santacarolina.financeiro.dao.BancoDAO;
import com.santacarolina.financeiro.dto.BancoDTO;
import com.santacarolina.financeiro.entity.BancoEntity;
import com.santacarolina.financeiro.repository.BancoRepository;

@RestController
@RequestMapping("/bancos")
@SuppressWarnings("rawtypes")
public class BancoController {

    private final Logger logger = LogManager.getLogger();
    private BancoDAO bancoDAO;
    private BancoRepository repository;

    @Autowired
    public BancoController(BancoDAO bancoDAO, BancoRepository repository) {
        this.bancoDAO = bancoDAO;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<BancoDTO>> findAll() {
        try {
            return ResponseEntity.ok(bancoDAO.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoDTO> findById(@PathVariable long id) {
        try {
            return bancoDAO.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/nomeBanco={nomeBanco}")
    public ResponseEntity<BancoDTO> findByNomeBanco(@PathVariable String nomeBanco) {
        try {
            return bancoDAO.findByNomeBanco(nomeBanco)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/apelido={apelido}")
    public ResponseEntity<BancoDTO> findByApelido(@PathVariable String apelido) {
        try {
            return bancoDAO.findByApelido(apelido)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody BancoEntity banco) {
        try {
            repository.save(banco);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (OptimisticLockingFailureException e) {
            logger.error(e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
