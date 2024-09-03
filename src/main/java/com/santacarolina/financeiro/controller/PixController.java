package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.models.dto.PixDTO;
import com.santacarolina.financeiro.models.entities.ChavePix;
import com.santacarolina.financeiro.models.services.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chavesPix")
public class PixController {

    @Autowired
    private PixService service;

    @GetMapping
    public List<PixDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/")
    public PixDTO findByChave (@RequestParam String chave) {
        return service.findByChavePix(chave).orElse(null);
    }

    @PostMapping
    public ChavePix save (@RequestBody ChavePix chavePix) {
        System.out.println(chavePix);
        service.save(chavePix);
        return chavePix;
    }

}
