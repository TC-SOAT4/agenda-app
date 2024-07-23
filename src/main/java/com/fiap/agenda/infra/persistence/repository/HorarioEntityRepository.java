package com.fiap.agenda.infra.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.agenda.infra.persistence.entity.HorarioEntity;

public interface HorarioEntityRepository extends JpaRepository<HorarioEntity, Long> {

    HorarioEntity findByIdHorarioAndAgendaIdAgenda(Long idHorario, UUID idAgenda);

}
