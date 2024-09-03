package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.models.dto.DadoDTO;
import com.santacarolina.financeiro.models.entities.DadoBancario;
import com.santacarolina.financeiro.models.services.DadoService;
import com.santacarolina.financeiro.repository.DadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class DadoController {

    @Autowired
    private DadoService service;

    @GetMapping
    public List<DadoDTO> getAll() {
        return service.findAll();
    }

}
