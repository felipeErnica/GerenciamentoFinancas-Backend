package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.models.PastaContabil;
import com.santacarolina.financeiro.repository.PastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pastaContabil")
public class PastaController {

    @Autowired
    private PastaRepository repository;

    @GetMapping
    public List<PastaContabil> getAll(){ return repository.findAll(); }

    @GetMapping("/")
    public Optional<PastaContabil> findByNome(@RequestParam String nome) { return repository.findByNomeIgnoreCase(nome); }

    @GetMapping("/{id}")
    public PastaContabil get(@PathVariable long id){
        Optional<PastaContabil> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public void addPasta (@RequestBody PastaContabil pasta){ repository.save(pasta); }

    @DeleteMapping("/{id}")
    public ResponseEntity<PastaContabil> deletePasta (@PathVariable long id){
        Optional<PastaContabil> p = repository.findById(id);
        if (p.isPresent()) {
            repository.delete(p.get());
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }
}
