package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dao.DadoDAO;
import com.santacarolina.financeiro.dao.PixDAO;
import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.util.DataBaseConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contas")
@SuppressWarnings("rawtypes")
public class DadoController {

    private DadoDAO dadoDAO;
    private PixDAO pixDAO;

    @Autowired
    public DadoController(DataBaseConn conn) {
        this.dadoDAO = new DadoDAO(conn);
        this.pixDAO = new PixDAO(conn);
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
    public ResponseEntity<DadoDTO> save(@RequestBody DadoDTO d) {
        try {
            dadoDAO.save(d);
            PixDTO pix = d.getPixDTO();
            if (pix != null){
                pix.setDadoId(d.getId());
                pixDAO.save(d.getPixDTO());
            } 
            return ResponseEntity.ok(d);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById (@PathVariable long id) {
        try {
            dadoDAO.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

