package com.fiap.agenda.infra.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.agenda.infra.persistence.entity.AgendaEntity;

public interface AgendaEntityRepository extends JpaRepository<AgendaEntity, UUID> {

}
