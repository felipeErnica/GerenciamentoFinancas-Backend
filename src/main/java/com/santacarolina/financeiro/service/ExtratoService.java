package com.santacarolina.financeiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ExtratoDTO;
import com.santacarolina.financeiro.entity.ExtratoEntity;
import com.santacarolina.financeiro.repository.ExtratoRepository;

/**
 * ExtratoService
 */
@Service
public class ExtratoService {

    @Autowired
    private ExtratoRepository repository;

    public List<ExtratoDTO> findByContaId(long contaId) throws IllegalArgumentException {
        return repository.findByContaId(contaId).stream()
            .map(entity -> new ExtratoDTO(entity))
            .toList();
    }

    public List<ExtratoDTO> findByConciliacao(boolean isConciliado) throws IllegalArgumentException {
        return repository.findByConciliado(isConciliado).stream()
            .map(entity -> new ExtratoDTO(entity))
            .toList();
    }

    public void save(ExtratoEntity d) throws IllegalArgumentException, OptimisticLockingFailureException {
        repository.save(d);
    }

    public void saveAll(List<ExtratoEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        repository.saveAll(list);
    }

    public void deleteAllInBatch(List<ExtratoEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        list.forEach(extrato -> repository.delete(extrato));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

}
