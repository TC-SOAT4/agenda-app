package com.fiap.agenda.domain.usecase;

import java.util.UUID;

import com.fiap.agenda.application.medico.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.application.medico.controller.dto.AlterarAgendaDTO;

public interface IAlterarAgenda {

    AgendaResponseDTO executar(UUID idAgenda, AlterarAgendaDTO alterarAgendaDTO);

}