package com.fiap.agenda.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fiap.agenda.application.controller.dto.NovoAgendamentoConsultaDTO;
import com.fiap.agenda.conf.AuthenticationFacade;
import com.fiap.agenda.domain.client.IBuscarMedicoAppClient;
import com.fiap.agenda.domain.gateway.IAgendaGateway;
import com.fiap.agenda.domain.messaging.IAgendamentoConsultaQueueAdapter;
import com.fiap.agenda.domain.usecase.IRealizarAgendamentoDeConsulta;
import com.fiap.agenda.exceptions.HorarioNaoEncontradoException;
import com.fiap.agenda.infra.persistence.mapper.AgendaMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RealizarAgendamentoDeConsulta implements IRealizarAgendamentoDeConsulta {

    private final IAgendamentoConsultaQueueAdapter agendamentoConsultaQueueAdapter;

    private final IAgendaGateway agendaGateway;

    private final AgendaMapper agendaMapper;

    private final IBuscarMedicoAppClient buscarMedicoAppClient;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void executar(NovoAgendamentoConsultaDTO novoAgendamentoConsultaDTO, String bearerToken) {

        var horario = agendaGateway.buscarHorarioPorIdAndMedico(novoAgendamentoConsultaDTO.getIdHorario(),
                novoAgendamentoConsultaDTO.getIdMedico());

        if (horario == null)
            throw new HorarioNaoEncontradoException();

        // TODO Buscar dados dos pacientes

        var idPaciente = UUID.fromString("6f46f44c-f4d4-4b2b-acee-95d1558d037f");

        var agendamentoConsulta = agendaMapper.toAgendamentoConsulta(novoAgendamentoConsultaDTO);
        agendamentoConsulta.setIdPaciente(idPaciente);
        agendamentoConsulta.setInicio(horario.getInicio());
        agendamentoConsulta.setFim(horario.getInicio());
        agendamentoConsulta.setDia(horario.getAgenda().getDia());

        agendamentoConsultaQueueAdapter.enviar(agendamentoConsulta);

    }

}
