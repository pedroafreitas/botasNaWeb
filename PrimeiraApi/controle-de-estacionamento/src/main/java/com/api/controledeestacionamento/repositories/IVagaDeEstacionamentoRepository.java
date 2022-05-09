package com.api.controledeestacionamento.repositories;

import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IVagaDeEstacionamentoRepository extends JpaRepository<VagaDeEstacionamentoModel, UUID> {
    boolean existsByPlacaDoCarro(String placaDoCarro);
    boolean existsByNumeroDaVaga(String numeroDaVaga);
    boolean existsByApartamentoAndBloco(String apartamento, String bloco);
}