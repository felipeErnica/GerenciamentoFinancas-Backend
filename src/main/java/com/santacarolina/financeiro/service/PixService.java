package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;

import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.entity.PixEntity;
import com.santacarolina.financeiro.repository.PixRepository;

/**
 * PixService
 */
public class PixService {

    @Autowired
    private PixRepository repository;

    public List<PixDTO> findAll() {
        return repository.findAll().stream()
            .map(entity -> new PixDTO(entity))
            .toList();
    }

    public Optional<PixDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new PixDTO(entity));
    }

    public Optional<PixDTO> findByChavePix(String chave) throws IllegalArgumentException {
        return repository.findByChave(chave)
            .map(entity -> new PixDTO(entity));
    }

    public List<PixDTO> findByContato(long contatoId) throws IllegalArgumentException {
        return repository.findByContato(contatoId).stream()
            .map(entity -> new PixDTO(entity))
            .toList();
    }

    public Optional<PixDTO> findByDado(long dadoId) throws IllegalArgumentException {
        return repository.findByDado(dadoId)
            .map(entity -> new PixDTO(entity));
    }

    public void save(PixEntity chavePix) throws IllegalArgumentException, OptimisticLockingFailureException {
        repository.save(chavePix);
    }

    public void deleteById(long id) throws IllegalArgumentException {
        repository.deleteById(id);
    }
}
