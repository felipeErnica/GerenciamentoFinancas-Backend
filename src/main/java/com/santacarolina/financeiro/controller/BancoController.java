package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.BancoDAO;
import com.santacarolina.financeiro.dto.BancoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bancos")
public class BancoController {

    @Autowired
    private BancoDAO bancoDAO;

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

    @PostMapping
    public ResponseEntity save(@RequestBody BancoDTO banco) {
        try {
            bancoDAO.save(banco);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        try {
            bancoDAO.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
