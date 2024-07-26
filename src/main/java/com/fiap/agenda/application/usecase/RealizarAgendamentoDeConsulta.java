package com.fiap.agenda.application.usecase;

import org.springframework.stereotype.Component;

import com.fiap.agenda.application.controller.dto.NovoAgendamentoConsultaDTO;
import com.fiap.agenda.conf.AuthenticationFacade;
import com.fiap.agenda.domain.client.IPacienteClient;
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

    private final AuthenticationFacade authenticationFacade;

    private final IPacienteClient pacienteClient;

    @Override
    public void executar(NovoAgendamentoConsultaDTO novoAgendamentoConsultaDTO, String bearerToken) {

        var horario = agendaGateway.buscarHorarioPorIdAndMedico(novoAgendamentoConsultaDTO.getIdHorario(),
                novoAgendamentoConsultaDTO.getIdMedico());

        if (horario == null)
            throw new HorarioNaoEncontradoException();

        var cpf = authenticationFacade.getAuthentication().getName();
        var paciente = pacienteClient.buscarPacienteByCpf(cpf, bearerToken);

        var agendamentoConsulta = agendaMapper.toAgendamentoConsulta(novoAgendamentoConsultaDTO);
        agendamentoConsulta.setIdPaciente(paciente.getId());
        agendamentoConsulta.setInicio(horario.getInicio());
        agendamentoConsulta.setFim(horario.getInicio());
        agendamentoConsulta.setDia(horario.getAgenda().getDia());

        agendamentoConsultaQueueAdapter.enviar(agendamentoConsulta);

    }

}
