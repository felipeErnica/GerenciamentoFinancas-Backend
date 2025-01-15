package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;

import com.santacarolina.financeiro.dto.BancoDTO;
import com.santacarolina.financeiro.entity.BancoEntity;
import com.santacarolina.financeiro.repository.BancoRepository;

/**
 * BancoService
 */
public class BancoService {

    @Autowired
    private BancoRepository repository;

    public List<BancoDTO> findAll() throws IllegalArgumentException {
        return repository.findAll().stream()
                .map(entity -> new BancoDTO(entity))
                .toList();
    }

    public Optional<BancoDTO> findById (long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new BancoDTO(entity));
    }

    public Optional<BancoDTO> findByNomeBanco(String nomeBanco) {
        return repository.findByNomeBanco(nomeBanco)
            .map(entity -> new BancoDTO(entity));
    }

    public Optional<BancoDTO> findByApelido(String apelido) {
        return repository.findByApelidoBanco(apelido)
            .map(entity -> new BancoDTO(entity));
    }

    public void save(BancoEntity banco) throws IllegalArgumentException, OptimisticLockingFailureException {
        repository.save(banco);
    }

    public void deleteById(long id) throws IllegalArgumentException {
        repository.deleteById(id);
    }

}
