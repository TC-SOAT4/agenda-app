package com.fiap.agenda.application.medico.usecase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fiap.agenda.domain.gateway.IAgendaGateway;
import com.fiap.agenda.domain.usecase.IConfirmarAgendamento;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ConfirmarAgedamento implements IConfirmarAgendamento {

     private final IAgendaGateway agendaGateway;

    @Override
    public void executar(UUID idAgenda, Long idHorario) {
        agendaGateway.confirmarAgendamento(idAgenda, idHorario);
    }

}
