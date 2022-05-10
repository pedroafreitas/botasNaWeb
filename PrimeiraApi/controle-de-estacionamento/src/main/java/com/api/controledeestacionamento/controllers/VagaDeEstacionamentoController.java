package com.api.controledeestacionamento.controllers;

import com.api.controledeestacionamento.dtos.VagaDeEstacionamentoDto;
import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;
import com.api.controledeestacionamento.services.IVagaDeEstacionamentoService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class VagaDeEstacionamentoController {

    final IVagaDeEstacionamentoService vagaDeEstacionamentoService;

    public VagaDeEstacionamentoController(IVagaDeEstacionamentoService vagaDeEstacionamentoService) {
        this.vagaDeEstacionamentoService = vagaDeEstacionamentoService;
    }

    @PostMapping
    @RequestMapping("/vagas/inserir-vaga")
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
    @RequestMapping("/vagas/listar-vagas")
    public ResponseEntity<List<VagaDeEstacionamentoModel>> getTodasAsVagasDeEstacionamento() {
        return ResponseEntity.status(HttpStatus.OK).body(vagaDeEstacionamentoService.findAll());
    }
    
    @GetMapping
    @RequestMapping("/vagas/{id}")
    public ResponseEntity<Object> getVagaDeEstacionamento(@PathVariable(value = "id") UUID idVaga)
    {
        Optional<VagaDeEstacionamentoModel> vagaDeEstacionamentoModelOptional = vagaDeEstacionamentoService.findById(idVaga);

        return vagaDeEstacionamentoModelOptional.<ResponseEntity<Object>>map(
                vagaDeEstacionamentoModel -> ResponseEntity.status(HttpStatus.OK).body(vagaDeEstacionamentoModel))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada"));
    }

    @DeleteMapping
    @RequestMapping("/vagas/{id}")
    public ResponseEntity<Object> deleteVagaDeEstacionamento(@PathVariable(value = "id") UUID idVaga)
    {
        Optional<VagaDeEstacionamentoModel> vagaDeEstacionamentoModelOptional = vagaDeEstacionamentoService.findById(idVaga);

        if(!vagaDeEstacionamentoModelOptional.isPresent())
        {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
        }
        vagaDeEstacionamentoService.deleteVaga(vagaDeEstacionamentoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga deletada");
    }

    @PutMapping
    @RequestMapping("/vagas/{id}")
    public ResponseEntity<Object> atualizarVagaDeEstacionamento(@PathVariable(value = "id") UUID idVaga,
                                                                                   @RequestBody @Valid VagaDeEstacionamentoDto vagaDeEstacionamentoDto)
    {
        Optional<VagaDeEstacionamentoModel> vagaDeEstacionamentoModelOptional = vagaDeEstacionamentoService.findById(idVaga);

        if(vagaDeEstacionamentoModelOptional.isEmpty())
        {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
        }
        var vagaDeEstacionamentoModel = new VagaDeEstacionamentoModel();
        BeanUtils.copyProperties(vagaDeEstacionamentoDto, vagaDeEstacionamentoModel);
        vagaDeEstacionamentoModel.setId(vagaDeEstacionamentoModelOptional.get().getId());
        vagaDeEstacionamentoModel.setDataDoRegistro(vagaDeEstacionamentoModelOptional.get().getDataDoRegistro());

        return ResponseEntity.status(HttpStatus.OK).body(vagaDeEstacionamentoService.salvar(vagaDeEstacionamentoModel));
    }
}
