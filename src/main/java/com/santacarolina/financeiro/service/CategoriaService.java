package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.CategoriaDTO;
import com.santacarolina.financeiro.entity.CategoriaEntity;
import com.santacarolina.financeiro.repository.CategoriaRepository;

import jakarta.persistence.OptimisticLockException;

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
        return repository.findByNome(nome.replace("+"," "))
            .map(entity -> new CategoriaDTO(entity));
    }

    public Optional<CategoriaDTO> findByNumero(String numero) throws IllegalArgumentException {
        return repository.findByNumeroCategoria(numero)
            .map(entity -> new CategoriaDTO(entity));
    }

    public void save(CategoriaEntity t) throws OptimisticLockException {
        repository.save(t);
    }

    public void deleteById(long id) throws OptimisticLockException {
        repository.deleteById(id);
    }

    public void deleteAll(List<CategoriaEntity> list) {
        list.forEach(categoria -> repository.deleteById(categoria.getId()));
    }
}
