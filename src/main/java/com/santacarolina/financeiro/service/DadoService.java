package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.entity.DadoEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.DadoRepository;

/**
 * DadoService
 */
@Service
public class DadoService {

    @Autowired
    private DadoRepository repository;

    public List<DadoDTO> findAll() {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByUser(user).stream()
            .map(entity -> new DadoDTO(entity))
            .toList();
    }

    public Optional<DadoDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new DadoDTO(entity));
    }

    public List<DadoDTO> findByContato(long contatoId) throws IllegalArgumentException {
        return repository.findByContato(contatoId).stream()
            .map(entity -> new DadoDTO(entity))
            .toList();
    }

    public Optional<DadoDTO> findEqual(String agencia, 
        String numeroConta, 
        long bancoId) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findEqual(agencia, numeroConta, bancoId, user)
            .map(entity -> new DadoDTO(entity));
    }

    public void save(DadoEntity d) throws IllegalArgumentException, OptimisticLockingFailureException {
        d.setUser(UserService.getLoggedUser());
        repository.save(d);
    }

    public void deleteById(long id) throws OptimisticLockingFailureException {
        repository.deleteById(id);
    }

    public void deleteAll(List<DadoEntity> list) {
        list.forEach(dado -> repository.deleteById(dado.getId()));
    }
}
