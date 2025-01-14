package com.santacarolina.financeiro.service;

import java.util.List;
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

    public List<CategoriaDTO> findAll() {
        return repository.findAll().stream()
            .map(entity -> new CategoriaDTO(entity))
            .toList();
    }

    public Optional<CategoriaDTO> findByNome(String nome) throws IllegalArgumentException {
        return repository.findByNome(nome)
            .map(entity -> new CategoriaDTO(entity));
    }

    public Optional<CategoriaDTO> findByNumero(String numero) throws IllegalArgumentException {
        return repository.findByNumeroCategoria(numero)
            .map(entity -> new CategoriaDTO(entity));
    }
}
