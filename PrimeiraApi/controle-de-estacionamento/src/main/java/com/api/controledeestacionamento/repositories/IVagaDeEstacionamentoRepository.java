package com.api.controledeestacionamento.repositories;

import com.api.controledeestacionamento.models.VagaDeEstacionamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IVagaDeEstacionamentoRepository extends JpaRepository<VagaDeEstacionamentoModel, UUID> {

}