package com.santacarolina.financeiro.services;

import com.santacarolina.financeiro.dto.PixDTO;
import com.santacarolina.financeiro.models.ChavePix;
import com.santacarolina.financeiro.repository.ContatoRepository;
import com.santacarolina.financeiro.repository.DadoRepository;
import com.santacarolina.financeiro.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PixService {

    private ContatoRepository contatoRepository;
    private PixRepository pixRepository;
    private DadoRepository dadoRepository;

    @Autowired
    public PixService(ContatoRepository contatoRepository, PixRepository pixRepository, DadoRepository dadoRepository) {
        this.contatoRepository = contatoRepository;
        this.pixRepository = pixRepository;
        this.dadoRepository = dadoRepository;
    }

    public List<PixDTO> findAll() {
        return pixRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<PixDTO> getPixByContato(long id) {
        return contatoRepository.getPix(id).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PixDTO> findById(long id) { return pixRepository.findById(id).map(this::toDto); }
    public Optional<PixDTO> findByChavePix (String chave) { return pixRepository.findByChave(chave).map(this::toDto); }
    public void save(ChavePix chavePix) { pixRepository.save(chavePix); }

    public ResponseEntity<ChavePix> delete(long id) {
        Optional<ChavePix> pixOptional = pixRepository.findById(id);
        if (pixOptional.isPresent()) {
            ChavePix c = pixOptional.get();
            if (c.getDadoBancario() != null) {
                c.getDadoBancario().setChavePix(null);
                dadoRepository.save(c.getDadoBancario());
            }
            pixRepository.delete(c);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }

    private PixDTO toDto (ChavePix c) {
        return new PixDTO(c.getId(),
                c.getContato(),
                c.getDadoBancario() != null ? c.getDadoBancario().getId() : null,
                c.getTipoPix(),
                c.getChave());
    }

}
