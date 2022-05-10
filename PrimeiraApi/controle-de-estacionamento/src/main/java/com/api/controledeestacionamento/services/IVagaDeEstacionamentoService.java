package com.api.controledeestacionamento.services;

import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IVagaDeEstacionamentoService {

    Object salvar(VagaDeEstacionamentoModel vagaDeEstacionamento);

    boolean existePorPlacaDoCarro(String placaDoCarro);

    boolean existePorNumeroDaVaga(String numeroDaVaga);

    boolean existePorApartamentoEBloco(String apartamento, String bloco);

    List<VagaDeEstacionamentoModel> findAll();

    Optional<VagaDeEstacionamentoModel> findById(UUID idVaga);

    void deleteVaga(VagaDeEstacionamentoModel vagaDeEstacionamentoModel);
}
