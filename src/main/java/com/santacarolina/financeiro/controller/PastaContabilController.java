package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.models.entities.PastaContabil;
import com.santacarolina.financeiro.repository.PastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pasta")
public class PastaContabilController {

    @Autowired
    private PastaRepository repository;

    @GetMapping
    public List<PastaContabil> getAll(){
                                return repository.findAll();
                                                            }

    @GetMapping("/{id}")
    public PastaContabil get(@PathVariable long id){
        Optional<PastaContabil> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public void addPasta (@RequestBody PastaContabil pasta){
        repository.save(pasta);
    }

    @DeleteMapping("/{id}")
    public void deletePasta (@PathVariable long id){
        repository.deleteById(id);
    }
}
