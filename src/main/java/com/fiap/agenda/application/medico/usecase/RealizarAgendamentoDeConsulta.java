package com.fiap.agenda.application.medico.usecase;

import org.springframework.stereotype.Component;

import com.fiap.agenda.application.medico.controller.dto.NovoAgendamentoConsultaDTO;
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

    @Override
    public void executar(NovoAgendamentoConsultaDTO novoAgendamentoConsultaDTO) {

        var horario = agendaGateway.buscarHorarioPorIdAndMedico(novoAgendamentoConsultaDTO.getIdHorario(), novoAgendamentoConsultaDTO.getIdMedico());

        if(horario == null)
            throw new HorarioNaoEncontradoException();

        var agendamentoConsulta = agendaMapper.toAgendamentoConsulta(novoAgendamentoConsultaDTO);
        agendamentoConsulta.setIdPaciente(null);
        agendamentoConsulta.setInicio(horario.getInicio());
        agendamentoConsulta.setFim(horario.getInicio());
        agendamentoConsulta.setDia(horario.getAgenda().getDia());

        agendamentoConsultaQueueAdapter.enviar(agendamentoConsulta);

    }

}
