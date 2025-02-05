package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.entity.PixEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.PixRepository;

/**
 * PixService
 */
@Service
public class PixService {

    @Autowired
    private PixRepository repository;

    public List<PixDTO> findAll() {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByUser(user).stream()
            .map(entity -> new PixDTO(entity))
            .toList();
    }

    public Optional<PixDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new PixDTO(entity));
    }

    public Optional<PixDTO> findByChavePix(String chave) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByChave(chave, user)
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
        chavePix.setUser(UserService.getLoggedUser());
        repository.save(chavePix);
    }

    public void deleteById(long id) throws IllegalArgumentException {
        repository.deleteById(id);
    }

    public void deleteAll(List<PixEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        list.forEach(pix -> repository.deleteById(pix.getId()));
    }
}
