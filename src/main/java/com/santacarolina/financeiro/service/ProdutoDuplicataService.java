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

    private void addElements(List<ProdutoEntity> listProdutos, 
            List<DuplicataEntity> listDuplicatas,
            List<ProdutoDuplicataDTO> produtoDuplicataList) {

        for (ProdutoEntity produto : listProdutos) {
            for (DuplicataEntity duplicata : listDuplicatas) {
                ProdutoDuplicataDTO produtoDuplicataDTO = new ProdutoDuplicataDTO(produto, duplicata);
                produtoDuplicataList.add(produtoDuplicataDTO);
            }
        }
    }

    private List<ProdutoDuplicataDTO> buildList(Map<Long, List<ProdutoEntity>> filteredProd,
            Map<Long, List<DuplicataEntity>> filteredDups, 
            List<DocumentoEntity> documentoEntities) {

        List<ProdutoDuplicataDTO> produtoDuplicataList = new ArrayList<>();
        for (DocumentoEntity documento : documentoEntities) {
            long documentoId = documento.getId();
            List<ProdutoEntity> listProdutos = filteredProd.getOrDefault(documentoId, Collections.emptyList());
            List<DuplicataEntity> listDuplicatas = filteredDups.getOrDefault(documentoId, Collections.emptyList());
            int numDup = listDuplicatas.size();
            listProdutos.forEach(prod -> prod.setQuantidade(prod.getQuantidade()/numDup));
            addElements(listProdutos, listDuplicatas, produtoDuplicataList);
        }

        return produtoDuplicataList;
    }

    public List<ProdutoDuplicataDTO> findProdutosDuplicatas() {
        List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
        List<DuplicataEntity> duplicataEntities = duplicataRepository.findAll();
        List<DocumentoEntity> documentoEntities = documentoRepository.findAll();
        
        Map<Long, List<ProdutoEntity>> filteredProd = produtoEntities.stream()
            .collect(Collectors.groupingBy(prod -> prod.getDocumento().getId()));
        Map<Long, List<DuplicataEntity>> filteredDups = duplicataEntities.stream()
            .collect(Collectors.groupingBy(dup -> dup.getDocumento().getId()));

        return buildList(filteredProd, filteredDups, documentoEntities);

    }

    public List<ProdutoDuplicataDTO> findTest() {
        return produtoRepository.findProdutosDuplicatas();
    }

}
