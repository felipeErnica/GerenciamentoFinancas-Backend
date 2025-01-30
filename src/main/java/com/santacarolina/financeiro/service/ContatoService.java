package com.santacarolina.financeiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ContatoDTO;
import com.santacarolina.financeiro.entity.ContatoEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.repository.ContatoRepository;

/**
 * ContatoService
 */
@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public List<ContatoDTO> findAll() {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByUser(user).stream()
            .map(entity -> new ContatoDTO(entity))
            .toList();
    }

    public Optional<ContatoDTO> findByCpf(String cpf) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByCpfAndUser(cpf, user)
            .map(entity -> new ContatoDTO(entity));
    }

    public Optional<ContatoDTO> findByCnpj(String cnpj) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByCnpjAndUser(cnpj, user)
            .map(entity -> new ContatoDTO(entity));
    }

    public Optional<ContatoDTO> findByIe(String ie) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByIeAndUser(ie, user)
            .map(entity -> new ContatoDTO(entity));
    }

    public Optional<ContatoDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new ContatoDTO(entity));
    }

    public Optional<ContatoDTO> findByNome(String nome) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByNomeAndUser(nome.replace("+", " "), user)
            .map(entity -> new ContatoDTO(entity));
    }

    public void saveAll(List<ContatoEntity> contatos) throws OptimisticLockingFailureException, IllegalArgumentException {
        repository.saveAll(contatos);
    }

    public void save(ContatoEntity contato) throws IllegalArgumentException, OptimisticLockingFailureException {
        repository.save(contato);
    }

    public void deleteById(long id) throws IllegalArgumentException {
        repository.deleteById(id);
    }

    public void deleteAll(List<ContatoEntity> list) {
        list.forEach(contato -> repository.deleteById(contato.getId()));
    }

}
