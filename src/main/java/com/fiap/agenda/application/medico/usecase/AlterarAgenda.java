package com.fiap.agenda.application.medico.usecase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fiap.agenda.application.medico.controller.dto.AgendaResponseDTO;
import com.fiap.agenda.application.medico.controller.dto.AlterarAgendaDTO;
import com.fiap.agenda.domain.gateway.IAgendaGateway;
import com.fiap.agenda.domain.usecase.IAlterarAgenda;
import com.fiap.agenda.infra.persistence.mapper.AgendaMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AlterarAgenda implements IAlterarAgenda {

    private final IAgendaGateway agendaGateway;

    private final AgendaMapper agendaMapper;

    @Override
    public AgendaResponseDTO executar(UUID idAgenda, AlterarAgendaDTO alterarAgendaDTO) {
        var agenda = agendaGateway.buscarPorId(idAgenda);
        if (alterarAgendaDTO.getAtivo() != null)
            agenda.setAtivo(alterarAgendaDTO.getAtivo());

        agenda.getHorarios().forEach(horarioSalvo -> {
            var idHorarioSalvo = horarioSalvo.getIdHorario();
            var horarioEncontrado = alterarAgendaDTO.getHorarios().stream()
                    .filter(h -> h.getIdHorario() != null)
                    .filter(h -> h.getIdHorario().equals(idHorarioSalvo)).findFirst();
            if (horarioEncontrado.isPresent()) {
                horarioSalvo.setInicio(horarioEncontrado.get().getInicio());
                horarioSalvo.setFim(horarioEncontrado.get().getFim());
            }
        });

        var listNovosHorarios = alterarAgendaDTO.getHorarios().stream().filter(h -> h.getIdHorario() == null)
                .map(agendaMapper::toHorario).toList();
        agenda.getHorarios().addAll(listNovosHorarios);

        agenda = agendaGateway.alterar(agenda);

        return agendaMapper.toAgendaResponseDTO(agenda);

    }

}
