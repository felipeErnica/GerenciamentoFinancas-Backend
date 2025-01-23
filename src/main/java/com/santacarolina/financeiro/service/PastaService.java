package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.PastaDTO;
import com.santacarolina.financeiro.entity.PastaEntity;
import com.santacarolina.financeiro.repository.PastaRepository;

/**
 * PastaService
 */
@Service
public class PastaService {

    @Autowired
    private PastaRepository repository;

    public List<PastaDTO> findAll() throws IllegalArgumentException {
        return repository.findAll().stream()
                .map(entity -> new PastaDTO(entity))
                .toList();
    }

    public Optional<PastaDTO> findByNome(String nome) throws IllegalArgumentException {
        return repository.findByNome(nome.replace("+"," "))
                .map(entity -> new PastaDTO(entity));

    }

    public Optional<PastaDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
                .map(entity -> new PastaDTO(entity));
    }

    public void save(PastaEntity pasta) throws IllegalArgumentException, OptimisticLockingFailureException {
        repository.save(pasta);
    }

    public void deleteById(long id) throws OptimisticLockingFailureException {
        repository.deleteById(id);
    }

    public void deleteAll(List<PastaEntity> list) {
        list.forEach(pasta -> repository.deleteById(pasta.getId()));
    }

}
