package com.api.controledeestacionamento.services;

import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;

import java.util.List;

public interface IVagaDeEstacionamentoService {

    Object salvar(VagaDeEstacionamentoModel vagaDeEstacionamento);

    boolean existePorPlacaDoCarro(String placaDoCarro);

    boolean existePorNumeroDaVaga(String numeroDaVaga);

    boolean existePorApartamentoEBloco(String apartamento, String bloco);

    List<VagaDeEstacionamentoModel> findAll();
}
