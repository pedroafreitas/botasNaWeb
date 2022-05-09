package com.api.controledeestacionamento.services;

import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;
import com.api.controledeestacionamento.repositories.IVagaDeEstacionamentoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VagaDeEstacionamentoService  implements IVagaDeEstacionamentoService {

    final IVagaDeEstacionamentoRepository vagaDeEstacionamentoRepository;

    public VagaDeEstacionamentoService(IVagaDeEstacionamentoRepository vagaDeEstacionamentoRepository) {
        this.vagaDeEstacionamentoRepository = vagaDeEstacionamentoRepository;
    }

    //se der alguma coisa errada, ele vai dar um rollback
    @Transactional
    public Object salvar(VagaDeEstacionamentoModel vagaDeEstacionamento) {
        return vagaDeEstacionamentoRepository.save(vagaDeEstacionamento);
    }

    public boolean existePorPlacaDoCarro(String placaDoCarro) {
        return vagaDeEstacionamentoRepository.existsByPlacaDoCarro(placaDoCarro);
    }

    public boolean existePorNumeroDaVaga(String numeroDaVaga) {
        return vagaDeEstacionamentoRepository.existsByNumeroDaVaga(numeroDaVaga);
    }

    public boolean existePorApartamentoEBloco(String apartamento, String bloco) {
        return vagaDeEstacionamentoRepository.existsByApartamentoAndBloco(apartamento, bloco);
    }

    public List<VagaDeEstacionamentoModel> findAll() {
        return vagaDeEstacionamentoRepository.findAll();
    }
}
