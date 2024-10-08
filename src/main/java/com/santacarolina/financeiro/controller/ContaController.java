package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.ContaDAO;
import com.santacarolina.financeiro.dto.ContaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contasAdm")
public class ContaController {

    @Autowired
    private ContaDAO contaDAO;

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        try {
            return ResponseEntity.ok(contaDAO.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<ContaDTO> findEqual(@RequestParam String agencia,
                                                    @RequestParam String numeroConta,
                                                    @RequestParam long bancoId) {
        try {
            return contaDAO.findEqual(agencia, numeroConta, bancoId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable long id) {
        try {
            return contaDAO.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ContaDTO c) {
        try {
            contaDAO.save(c);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            contaDAO.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
