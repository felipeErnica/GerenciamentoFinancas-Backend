package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.models.Contato;
import com.santacarolina.financeiro.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository repository;

    @GetMapping
    public List<Contato> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Contato getContato(@PathVariable long id){
        Optional<Contato> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @GetMapping("/contato")
    public Contato getByCnpj(@RequestParam String cnpj){
        Optional<Contato> optional = repository.findByCnpj(cnpj);
        return optional.orElse(null);
    }

    @PostMapping("/postList")
    public void addContato(@RequestBody List<Contato> contatos){
        contatos.forEach(c->repository.save(c));
    }

    @PostMapping(value = "/post")
    public void addContato(@RequestBody Contato contato){
        repository.save(contato);
    }

    @DeleteMapping("/{id}")
    public void deleteContato(@PathVariable long id){
        repository.deleteById(id);
    }

}
