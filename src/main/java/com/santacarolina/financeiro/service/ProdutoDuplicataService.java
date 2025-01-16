package com.santacarolina.financeiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santacarolina.financeiro.dto.ProdutoDuplicataDTO;
import com.santacarolina.financeiro.entity.DocumentoEntity;
import com.santacarolina.financeiro.entity.DuplicataEntity;
import com.santacarolina.financeiro.entity.ProdutoEntity;
import com.santacarolina.financeiro.repository.DocumentoRepository;

/**
 * ProdutoDuplicataService
 */
@Service
public class ProdutoDuplicataService {

    @Autowired
    private DocumentoRepository documentoRepository;

    private void mergeDuplicatas(ProdutoEntity produto, List<DuplicataEntity> duplicatas, List<ProdutoDuplicataDTO> produtoDuplicataList) {
        for (DuplicataEntity duplicata : duplicatas) {
            ProdutoDuplicataDTO dto = new ProdutoDuplicataDTO(produto, duplicata);
            produtoDuplicataList.add(dto);
        }
    }

    private void getProdutoDuplicataList(DocumentoEntity documento, List<ProdutoDuplicataDTO> produtoDuplicataList) {
        List<ProdutoEntity> produtos = documento.getProdutoList();
        List<DuplicataEntity> duplicatas = documento.getDuplicataList();
        for (ProdutoEntity produto : produtos) {
            produto.setValorUnit(produto.getValorUnit()/duplicatas.size());
            mergeDuplicatas(produto, duplicatas, produtoDuplicataList);
        }
    }

    public List<ProdutoDuplicataDTO> findProdutosDuplicatas() {
        List<DocumentoEntity> listDocumentos = documentoRepository.findAll();
        List<ProdutoDuplicataDTO> produtoDuplicataList = new ArrayList<>();
        for (DocumentoEntity documento : listDocumentos) {
            getProdutoDuplicataList(documento, produtoDuplicataList);
        }
        return produtoDuplicataList;
    }

}
