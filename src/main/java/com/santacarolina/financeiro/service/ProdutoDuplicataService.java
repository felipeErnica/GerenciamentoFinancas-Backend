package com.santacarolina.financeiro.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;
import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.entity.ProdutoEntity;
import com.santacarolina.financeiro.repository.DuplicataRepository;
import com.santacarolina.financeiro.repository.ProdutoRepository;

/**
 * ProdutoDuplicataService
 */
@Service
public class ProdutoDuplicataService {

    private ProdutoRepository produtoRepository;
    private DuplicataRepository duplicataRepository;

    @Autowired
    public ProdutoDuplicataService(ProdutoRepository produtoRepository, DuplicataRepository duplicataRepository) {
        this.produtoRepository = produtoRepository;
        this.duplicataRepository = duplicataRepository;
    }

    private List<ProdutoDuplicataDTO> buildListProdutoDuplicatas(List<ProdutoEntity> produtoEntities,
            Map<Long, List<DuplicataEntity>> duplicatas) {
        
        List<ProdutoDuplicataDTO> produtoDuplicataList = new ArrayList<>();
        for (ProdutoEntity produto : produtoEntities) {
            long documentoId = produto.getDocumento().getId();
            List<DuplicataEntity> duplicataEntities = duplicatas.getOrDefault(documentoId, Collections.emptyList());
            int listSize = duplicataEntities.size();
            produto.setValorUnit(produto.getValorUnit()/listSize);
            for (DuplicataEntity duplicata : duplicataEntities) {
                ProdutoDuplicataDTO produtoDuplicataDTO = new ProdutoDuplicataDTO(produto, duplicata);
                produtoDuplicataList.add(produtoDuplicataDTO);
            }
        }
        return produtoDuplicataList;
    }

    private Map<Long, List<DuplicataEntity>> mergeDuplicatas(List<DuplicataEntity> duplicataEntities) {
        return duplicataEntities.stream()
            .collect(Collectors.groupingBy(dup -> dup.getDocumento().getId()));
    }

    public List<ProdutoDuplicataDTO> findProdutosDuplicatas() {
        List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
        List<DuplicataEntity> duplicataEntities = duplicataRepository.findAll();
        
        Map<Long, List<DuplicataEntity>> duplicatas = mergeDuplicatas(duplicataEntities);
        return buildListProdutoDuplicatas(produtoEntities, duplicatas);
    }

}
