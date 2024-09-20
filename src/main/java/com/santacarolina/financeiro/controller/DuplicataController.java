package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.DuplicataDAO;
import com.santacarolina.financeiro.dto.DuplicataDTO;
import com.santacarolina.financeiro.models.Duplicata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/duplicatas")
public class DuplicataController {

    @Autowired
    private DuplicataDAO dao;

    @GetMapping
    public ResponseEntity<List<DuplicataDTO>> findAllHomePage() {
        try {
            return ResponseEntity.ok(dao.findAll());
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/isPayed={isPayed}")
    public ResponseEntity<List<DuplicataDTO>> findByConciliacao(@PathVariable boolean isPayed) {
        try {
            return ResponseEntity.ok(dao.findByConciliacao(isPayed));
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<DuplicataDTO> save(@RequestBody DuplicataDTO d) {
        try {
            dao.save(d);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/batch")
    public ResponseEntity saveAll(@RequestBody List<DuplicataDTO> list) {
        try {
            dao.saveAll(list);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
