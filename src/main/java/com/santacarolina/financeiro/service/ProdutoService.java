package com.santacarolina.financeiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ProdutoDTO;
import com.santacarolina.financeiro.entity.ProdutoEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.ProdutoRepository;

/**
 * ProdutoService
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> findAll() {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByUser(user).stream()
            .map(entity -> new ProdutoDTO(entity))
            .toList();
    }

    public List<ProdutoDTO> findByDoc(long documentoId) throws IllegalArgumentException {
        return repository.findByDoc(documentoId).stream()
            .map(entity -> new ProdutoDTO(entity))
            .toList();
    }

    public void saveAll(List<ProdutoEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        UserEntity user = UserService.getLoggedUser();
        list.forEach(prod -> prod.setUser(user));
        repository.saveAll(list);
    }

    public void deleteById(long id) throws IllegalArgumentException {
        repository.deleteById(id);
    }

    public void deleteAll(List<ProdutoEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        list.forEach(prod -> repository.deleteById(prod.getId()));
    }

    public void save(ProdutoEntity entity) throws IllegalArgumentException, OptimisticLockingFailureException {
        repository.save(entity);
    }
}
