package com.api.controledeestacionamento.controllers;

import com.api.controledeestacionamento.dtos.VagaDeEstacionamentoDto;
import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;
import com.api.controledeestacionamento.services.IVagaDeEstacionamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vaga-estacionamento")
public class VagaDeEstacionamentoController {

    final IVagaDeEstacionamentoService vagaDeEstacionamentoService;

    public VagaDeEstacionamentoController(IVagaDeEstacionamentoService vagaDeEstacionamentoService) {
        this.vagaDeEstacionamentoService = vagaDeEstacionamentoService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarVagaDEstacionamento(@RequestBody @Valid VagaDeEstacionamentoDto vagaDeEstacionamentoDto) {
        var vagaDeEstacionamento = new VagaDeEstacionamentoModel();
        BeanUtils.copyProperties(vagaDeEstacionamentoDto, vagaDeEstacionamento);
        vagaDeEstacionamento.setDataDoRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaDeEstacionamentoService.salvarVagaDeEstacionamento(vagaDeEstacionamento));
    }
}
