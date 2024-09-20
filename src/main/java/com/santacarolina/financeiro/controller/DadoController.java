package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.models.DadoBancario;
import com.santacarolina.financeiro.services.DadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class DadoController {

    @Autowired
    private DadoService service;

    @GetMapping
    public List<DadoDTO> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Optional<DadoDTO> findById(@PathVariable long id) { return service.findById(id); }

    @GetMapping("/info")
    public DadoDTO getEqual(@RequestParam String agencia, String numeroConta, long banco) {
        return service.getEqual(agencia, numeroConta, banco).orElse(null);
    }

    @PostMapping
    public void save(@RequestBody DadoBancario d) { service.save(d); }

    @DeleteMapping("/{id}")
    public ResponseEntity<DadoBancario> deleteById (@PathVariable long id) { return service.deleteById(id); }

}

