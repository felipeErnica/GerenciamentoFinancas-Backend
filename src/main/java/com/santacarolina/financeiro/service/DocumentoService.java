package com.santacarolina.financeiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.DocumentoDTO;
import com.santacarolina.financeiro.entity.DocumentoEntity;
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
        return repository.findAll().stream()
            .map(entity -> new DocumentoDTO(entity))
            .toList();
    }

    public Optional<DocumentoDTO> findById(long id) throws IllegalArgumentException {
        return repository.findById(id)
            .map(entity -> new DocumentoDTO(entity));
    }

    public Optional<DocumentoDTO> findEqual(long contatoId, TipoDocumento tipoDoc, LocalDate dataEmissao, long pastaId,
            double valor) throws IllegalArgumentException {
        return repository.findEqual(contatoId, tipoDoc, dataEmissao, pastaId, valor)
            .map(entity -> new DocumentoDTO(entity));
    }

    public Optional<DocumentoDTO> findNotaEqual(long contatoId, long numDoc) {
        return repository.findNotaEqual(contatoId, numDoc)
            .map(entity -> new DocumentoDTO(entity));
    }

    public void save(DocumentoEntity documento) throws IllegalArgumentException, OptimisticLockingFailureException {
        documento.getProdutoList().forEach(prod -> prod.setDocumento(documento));
        documento.getDuplicataList().forEach(dup -> dup.setDocumento(documento));
        repository.save(documento);
    }

    public void deleteById(long id) throws OptimisticLockingFailureException { repository.deleteById(id); }

    public void deleteBatch(List<DocumentoEntity> list) throws IllegalArgumentException, OptimisticLockingFailureException {
        list.forEach(doc -> repository.delete(doc));
    }
}
