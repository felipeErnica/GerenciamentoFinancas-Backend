package com.santacarolina.financeiro.service;

import java.util.ArrayList;
import java.util.List;

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

    private void addElements(
        ProdutoEntity produto,
        List<DuplicataEntity> duplicataEntities, 
        List<ProdutoDuplicataDTO> produtoDuplicataList,
        long index) {

        List<DuplicataEntity> filterDuplicatas = duplicataEntities.stream()
            .filter(dup -> dup.getDocumento().getId() == produto.getDocumento().getId())
            .toList();
        produto.setValorUnit(produto.getValorUnit()/filterDuplicatas.size());

        for (DuplicataEntity dup : filterDuplicatas) {
            ProdutoDuplicataDTO dto = new ProdutoDuplicataDTO(produto, dup);
            produtoDuplicataList.add(dto);
        }
    }

    private List<ProdutoDuplicataDTO> getProdutoDuplicataList(List<ProdutoEntity> produtoEntities, List<DuplicataEntity> duplicataEntities) {
        List<ProdutoDuplicataDTO> produtoDuplicataList = new ArrayList<>();
        for (ProdutoEntity produto : produtoEntities) {
            addElements(produto, duplicataEntities, produtoDuplicataList, produtoEntities.size());
        }
        return produtoDuplicataList;
    }

    public List<ProdutoDuplicataDTO> findProdutosDuplicatas() {
        List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
        List<DuplicataEntity> duplicataEntities = duplicataRepository.findAll();
        return getProdutoDuplicataList(produtoEntities, duplicataEntities);
    }

}
