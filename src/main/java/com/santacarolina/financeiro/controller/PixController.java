package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.models.ChavePix;
import com.santacarolina.financeiro.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chavesPix")
public class PixController {

    @Autowired
    private PixService service;

    @GetMapping
    public List<PixDTO> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Optional<PixDTO> findById(@PathVariable long id) { return service.findById(id); }

    @GetMapping("/")
    public PixDTO findByChave (@RequestParam String chave) { return service.findByChavePix(chave).orElse(null); }

    @PostMapping
    public ChavePix save (@RequestBody ChavePix chavePix) {
        service.save(chavePix);
        return chavePix;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChavePix> deleteById (@PathVariable long id) {
        System.out.println("Delete id: " + id);
        return service.delete(id);
    }

}
