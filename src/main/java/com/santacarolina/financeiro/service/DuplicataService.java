package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.DuplicataDTO;
import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.DuplicataRepository;

/**
 * DuplicataService
 */
@Service
public class DuplicataService {

    @Autowired
    private DuplicataRepository repository;

    public List<DuplicataDTO> findAll() {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByUser(user).stream()
            .map(entity -> new DuplicataDTO(entity))
            .toList();
    }

    public Optional<DuplicataDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new DuplicataDTO(entity));
    }

    public List<DuplicataDTO> findPagas() throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findPagas(user).stream()
            .map(entity -> new DuplicataDTO(entity))
            .toList();
    }

    public List<DuplicataDTO> findNaoPagas() throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findNaoPagas(user).stream()
            .map(entity -> new DuplicataDTO(entity))
            .toList();
    }

    public List<DuplicataDTO> findByDoc(long documentoId) throws IllegalArgumentException {
        return repository.findByDoc(documentoId).stream()
            .map(entity -> new DuplicataDTO(entity))
            .toList();
    }

    public void save(DuplicataEntity d) throws IllegalArgumentException, OptimisticLockingFailureException {
        d.setUser(UserService.getLoggedUser());
        repository.save(d); 
    }

    public void saveAll(List<DuplicataEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        UserEntity user = UserService.getLoggedUser();
        list.forEach(dup -> dup.setUser(user));
        repository.saveAll(list); 
    }
    
    public void deleteById(long id) throws IllegalArgumentException {
        repository.deleteById(id); 
    }

    public void deleteAll(List<DuplicataEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        list.forEach(dup -> repository.deleteById(dup.getId()));
    }

}
