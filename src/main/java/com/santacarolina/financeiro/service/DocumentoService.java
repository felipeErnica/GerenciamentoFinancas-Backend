package com.santacarolina.financeiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.entity.UserEntity;
import com.santacarolina.financeiro.enums.TipoDocumento;
import com.santacarolina.financeiro.repository.DocumentoRepository;

/**
 * DocumentoService
 */
@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository repository;

    public List<DocumentoDTO> findAll() {
        UserEntity user = UserService.getLoggedUser();
        return repository.findByUser(user).stream()
            .map(entity -> new DocumentoDTO(entity))
            .toList();
    }

    public Optional<DocumentoDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new DocumentoDTO(entity));
    }

    public Optional<DocumentoDTO> findEqual(long contatoId, TipoDocumento tipoDoc, LocalDate dataEmissao, long pastaId,
            double valor) throws IllegalArgumentException {
        UserEntity user = UserService.getLoggedUser();
        return repository.findEqual(contatoId, tipoDoc, dataEmissao, pastaId, valor, user)
            .map(entity -> new DocumentoDTO(entity));
    }

    public Optional<DocumentoDTO> findNotaEqual(long contatoId, long numDoc) {
        UserEntity user = UserService.getLoggedUser();
        return repository.findNotaEqual(contatoId, numDoc, user)
            .map(entity -> new DocumentoDTO(entity));
    }

    public void save(DocumentoEntity documento) throws IllegalArgumentException, OptimisticLockingFailureException {
        UserEntity user = UserService.getLoggedUser();
        documento.setUser(user);

        documento.getProdutoList().forEach(prod -> {
            prod.setUser(user);
            prod.setDocumento(documento);
        });

        documento.getDuplicataList().forEach(dup -> {
            dup.setDocumento(documento);
            dup.setUser(user);
        });

        repository.save(documento);
    }

    public void deleteById(long id) throws OptimisticLockingFailureException { repository.deleteById(id); }

    public void deleteBatch(List<DocumentoEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        list.forEach(doc -> repository.delete(doc));
    }
}
