package com.santacarolina.financeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.CategoriaDTO;
import com.santacarolina.financeiro.repository.CategoriaRepository;

/**
 * CategoriaService
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Optional<CategoriaDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new CategoriaDTO(entity));
    }
}
