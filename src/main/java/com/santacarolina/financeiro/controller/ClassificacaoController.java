package com.santacarolina.financeiro.controller;

import com.santacarolina.financeiro.models.ClassificacaoContabil;
import com.santacarolina.financeiro.repository.ClassificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classificacao")
public class ClassificacaoController {

    @Autowired
    private ClassificacaoRepository repository;

    @GetMapping
    private List<ClassificacaoContabil> getAll() {
        return repository.findAll();
    }

    @GetMapping(params = "numeroIdentificacao")
    private ClassificacaoContabil getByNumero (@RequestParam long numeroIdentificacao) {
        return repository.getByNumero(numeroIdentificacao);
    }

}
