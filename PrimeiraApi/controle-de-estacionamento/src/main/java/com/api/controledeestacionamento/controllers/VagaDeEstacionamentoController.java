package com.api.controledeestacionamento.controllers;

import com.api.controledeestacionamento.dtos.VagaDeEstacionamentoDto;
import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;
import com.api.controledeestacionamento.services.IVagaDeEstacionamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vaga-estacionamento")
public class VagaDeEstacionamentoController {

    final IVagaDeEstacionamentoService vagaDeEstacionamentoService;

    public VagaDeEstacionamentoController(IVagaDeEstacionamentoService vagaDeEstacionamentoService) {
        this.vagaDeEstacionamentoService = vagaDeEstacionamentoService;
    }

    @PostMapping
    @RequestMapping("/inserir-vaga")
    public ResponseEntity<Object> salvarVagaDEstacionamento
            (@RequestBody @Valid VagaDeEstacionamentoDto vagaDeEstacionamentoDto) {
        if(vagaDeEstacionamentoService.existePorPlacaDoCarro(vagaDeEstacionamentoDto.getPlacaDoCarro()) ||
                vagaDeEstacionamentoService.existePorNumeroDaVaga(vagaDeEstacionamentoDto.getNumeroDaVaga()) ||
                vagaDeEstacionamentoService.existePorApartamentoEBloco(vagaDeEstacionamentoDto.getApartamento(), vagaDeEstacionamentoDto.getBloco()))
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tentativa de inserir vaga com dados duplicado");
        }

        var vagaDeEstacionamento = new VagaDeEstacionamentoModel();
        
        //Copia um objeto para o outro
        BeanUtils.copyProperties(vagaDeEstacionamentoDto, vagaDeEstacionamento);
        vagaDeEstacionamento.setDataDoRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vagaDeEstacionamentoService.salvar(vagaDeEstacionamento));
    }

    @GetMapping
    @RequestMapping("/listar-vagas")
    public ResponseEntity<List<VagaDeEstacionamentoModel>> getTodasAsVagasDeEstacionamento() {
        return ResponseEntity.status(HttpStatus.OK).body(vagaDeEstacionamentoService.findAll());
    }
}
