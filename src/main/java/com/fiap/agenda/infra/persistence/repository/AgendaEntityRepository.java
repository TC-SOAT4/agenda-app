package com.fiap.agenda.infra.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.agenda.infra.persistence.entity.AgendaEntity;

public interface AgendaEntityRepository extends JpaRepository<AgendaEntity, UUID> {

    List<AgendaEntity> findAllByIdMedicoAndDia(UUID idMedico, LocalDate dia);

}
