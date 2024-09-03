package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.models.entities.Banco;
import com.santacarolina.financeiro.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bancos")
public class BancoController {

    @Autowired
    private BancoRepository repository;

    @GetMapping
    private List<Banco> getAll() { return repository.findAll(); }

}
