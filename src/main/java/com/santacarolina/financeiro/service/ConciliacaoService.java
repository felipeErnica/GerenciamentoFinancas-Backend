package com.santacarolina.financeiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ConciliacaoDTO;
import com.santacarolina.financeiro.entity.ConciliacaoEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.ConciliacaoRepository;

/**
 * ConciliacaoService
 */
@Service
public class ConciliacaoService {

    @Autowired
    private ConciliacaoRepository repository;

    public List<ConciliacaoDTO> findAll() {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByUser(user).stream()
            .map(entity -> new ConciliacaoDTO(entity))
            .toList();
    }

    public void save(ConciliacaoEntity c) throws IllegalArgumentException, OptimisticLockingFailureException {
        c.setUser(UserService.getLoggedUser());
        repository.save(c);
    }

    public void saveAll(List<ConciliacaoEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        UserEntity user = UserService.getLoggedUser();
        list.forEach(conciliacao -> conciliacao.setUser(user));
        repository.saveAll(list);
    }

    public void deleteById(long id) throws IllegalArgumentException {
        repository.deleteById(id);
    }

    public void deleteAll(List<ConciliacaoEntity> list) {
        list.forEach(conc -> repository.deleteById(conc.getId()));
    }

    public List<ConciliacaoDTO> findByExtrato(long extratoId) {
        return repository.findByExtrato(extratoId).stream()
            .map(conciliacao -> new ConciliacaoDTO(conciliacao))
            .toList();
    }

    public List<ConciliacaoDTO> findByDuplicata(long duplicataId) {
        return repository.findByDuplicata(duplicataId).stream()
            .map(conciliacao -> new ConciliacaoDTO(conciliacao))
            .toList();
    }
}
