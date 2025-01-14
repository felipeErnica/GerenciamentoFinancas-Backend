package com.santacarolina.financeiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ClassificacaoDTO;
import com.santacarolina.financeiro.repository.ClassificacaoRepository;

/**
 * ClassificacaoService
 */
@Service
public class ClassificacaoService {

    @Autowired
    private ClassificacaoRepository repository;

    public List<ClassificacaoDTO> findAll() {
        return repository.findAll().stream()
            .map(entity -> new ClassificacaoDTO(entity))
            .toList();
    }

}
