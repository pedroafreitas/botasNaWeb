package com.api.controledeestacionamento.services;

import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;
import com.api.controledeestacionamento.repositories.IVagaDeEstacionamentoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VagaDeEstacionamentoService  implements IVagaDeEstacionamentoService {

    final IVagaDeEstacionamentoRepository vagaDeEstacionamentoRepository;

    public VagaDeEstacionamentoService(IVagaDeEstacionamentoRepository vagaDeEstacionamentoRepository) {
        this.vagaDeEstacionamentoRepository = vagaDeEstacionamentoRepository;
    }


    @Transactional
    public Object salvarVagaDeEstacionamento(VagaDeEstacionamentoModel vagaDeEstacionamento) {
        return vagaDeEstacionamentoRepository.save(vagaDeEstacionamento);
    }
}
