package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ContaDTO;
import com.santacarolina.financeiro.entity.ContaEntity;
import com.santacarolina.financeiro.repository.ContaRepository;

/**
 * ContaService
 */
@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public List<ContaDTO> findAll() throws IllegalArgumentException {
        return repository.findAll().stream()
            .map(entity -> new ContaDTO(entity))
            .toList();
    }

    public Optional<ContaDTO> findEqual(String agencia, String numeroConta, long bancoId) throws IllegalArgumentException {
        return repository.findEqual(agencia, numeroConta, bancoId)
            .map(entity -> new ContaDTO(entity));
    }

    public Optional<ContaDTO> findById(long id) {
        return repository.findById(id)
            .map(entity -> new ContaDTO(entity));
    }

    public Optional<ContaDTO> findByApelido(String apelido) throws IllegalArgumentException {
        return repository.findByApelido(apelido.replace("+"," "))
            .map(entity -> new ContaDTO(entity));
    }

    public void save(ContaEntity c) throws IllegalArgumentException, OptimisticLockingFailureException {
        repository.save(c);
    }

    public void deleteById(long id) throws OptimisticLockingFailureException {
        repository.deleteById(id);
    }

    public void deleteAll(List<ContaEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        list.forEach(conta -> repository.deleteById(conta.getId()));
    }

}
