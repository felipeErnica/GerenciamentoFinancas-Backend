package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ClassificacaoDTO;
import com.santacarolina.financeiro.entity.ClassificacaoEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.ClassificacaoRepository;

import jakarta.persistence.OptimisticLockException;

/**
 * ClassificacaoService
 */
@Service
public class ClassificacaoService {

    @Autowired
    private ClassificacaoRepository repository;

    public List<ClassificacaoDTO> findAll() {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByUser(user).stream()
            .map(entity -> new ClassificacaoDTO(entity))
            .toList();
    }

    public Optional<ClassificacaoDTO> findById(long id) {
        return repository.findById(id)
            .map(entity -> new ClassificacaoDTO(entity));
    }

    public Optional<ClassificacaoDTO> findByNumero(String numeroIdentificacao) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByNumeroIdentificacao(numeroIdentificacao, user)
            .map(entity -> new ClassificacaoDTO(entity));
    }

    public Optional<ClassificacaoDTO> findByNome(String nome) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByNomeClassificacao(nome.replace("+"," "), user)
            .map(entity -> new ClassificacaoDTO(entity));
    }

    public void save(ClassificacaoEntity dto) throws OptimisticLockException {
        repository.save(dto);
    }

    public void deleteById(long id) throws OptimisticLockException {
        repository.deleteById(id);
    }

    public void deleteAll(List<ClassificacaoEntity> list) {
        list.forEach(classificacao -> repository.deleteById(classificacao.getId()));
    }

}
