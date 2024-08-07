package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.models.Contato;
import com.santacarolina.financeiro.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository repository;

    @GetMapping
    public List<Contato> getAll(){
        return repository.findAll();
    }

    @PostMapping
    public List<Contato> addContato(@RequestBody List<Contato> contatos){
        contatos.forEach(c->repository.save(c));
        return contatos;
    }

}
