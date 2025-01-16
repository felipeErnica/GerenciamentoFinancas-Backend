package com.santacarolina.financeiro.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;
import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.entity.ProdutoEntity;
import com.santacarolina.financeiro.repository.DocumentoRepository;
import com.santacarolina.financeiro.repository.DuplicataRepository;
import com.santacarolina.financeiro.repository.ProdutoRepository;

/**
 * ProdutoDuplicataService
 */
@Service
public class ProdutoDuplicataService {

    private ProdutoRepository produtoRepository;
    private DuplicataRepository duplicataRepository;
    private DocumentoRepository documentoRepository;

    @Autowired
    public ProdutoDuplicataService(
        ProdutoRepository produtoRepository, 
        DuplicataRepository duplicataRepository,
        DocumentoRepository documentoRepository) {

        this.produtoRepository = produtoRepository;
        this.duplicataRepository = duplicataRepository;
        this.documentoRepository = documentoRepository;
    }

    private void addDuplicata(List<ProdutoDuplicataDTO> produtoDuplicataList, 
        List<DuplicataEntity> filteredDups, 
        ProdutoEntity produto) {
        
        for (DuplicataEntity duplicata : filteredDups) {
            ProdutoDuplicataDTO produtoDuplicataDTO = new ProdutoDuplicataDTO(produto, duplicata);
            produtoDuplicataList.add(produtoDuplicataDTO);
        }
    }

    private void filterLists(
        List<DuplicataEntity> duplicataEntities, 
        List<ProdutoEntity> produtoEntities,
        List<ProdutoDuplicataDTO> produtoDuplicataList,
        long documentoId) {

        List<ProdutoEntity> filteredProd = produtoEntities.stream()
            .filter(prod -> prod.getDocumento().getId() == documentoId)
            .toList();
        List<DuplicataEntity> filteredDups = duplicataEntities.stream()
            .filter(dup -> dup.getDocumento().getId() == documentoId)
            .toList();

        int numDup = filteredDups.size();
        filteredProd.forEach(prod -> prod.setValorUnit(prod.getValorUnit()/numDup));

        for (ProdutoEntity produto : filteredProd) {
            addDuplicata(produtoDuplicataList, filteredDups, produto);
        }
    }

    public List<ProdutoDuplicataDTO> findProdutosDuplicatas() {
        List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
        List<DuplicataEntity> duplicataEntities = duplicataRepository.findAll();
        List<DocumentoEntity> documentoEntities = documentoRepository.findAll();
        List<ProdutoDuplicataDTO> produtoDuplicataList = new ArrayList<>();

        for (DocumentoEntity documento : documentoEntities) {
            long documentoId = documento.getId();
            filterLists(duplicataEntities, produtoEntities, produtoDuplicataList, documentoId);
        }

        return produtoDuplicataList;

    }

}
