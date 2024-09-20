package com.santacarolina.financeiro.services;

import com.santacarolina.financeiro.dto.DadoDTO;
import com.santacarolina.financeiro.models.DadoBancario;
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
public class DadoService {

    private ContatoRepository contatoRepository;
    private DadoRepository dadoRepository;
    private PixRepository pixRepository;

    @Autowired
    public DadoService(ContatoRepository contatoRepository, DadoRepository dadoRepository, PixRepository pixRepository) {
        this.contatoRepository = contatoRepository;
        this.dadoRepository = dadoRepository;
        this.pixRepository = pixRepository;
    }

    public List<DadoDTO> getDadosBancarios(long id) {
        return contatoRepository.getDadosBancarios(id).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DadoDTO> findById(long id) {
        return dadoRepository.findById(id).map(this::toDto);
    }

    public List<DadoDTO> findAll() {
        return dadoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void save(DadoBancario d) {
        if (d.getChavePix() != null) d.addChavePix(d.getChavePix());
        dadoRepository.save(d);
    }

    public Optional<DadoDTO> getEqual(String agencia, String numeroConta, long banco) {
        return dadoRepository.findEqual(agencia, numeroConta, banco).map(this::toDto);
    }

    private DadoDTO toDto (DadoBancario d) {
        return new DadoDTO(d.getId(),
                d.getAgencia(),
                d.getBanco(),
                d.getNumeroConta(),
                d.getContato(),
                d.getChavePix() != null ? d.getChavePix().getId() : null);
    }


    public ResponseEntity<DadoBancario> deleteById(long id) {
        Optional<DadoBancario> optional = dadoRepository.findById(id);
        if (optional.isPresent()) {
            DadoBancario d = optional.get();
            if (d.getChavePix() != null) {
                d.getChavePix().setDadoBancario(null);
                pixRepository.save(d.getChavePix());
            }
            dadoRepository.delete(d);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }

}
